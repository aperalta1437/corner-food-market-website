package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.form.SignupForm;
import com.cornerfoodmarketwebsite.business.service.utils.EmailTemplateCustomEnum;
import com.cornerfoodmarketwebsite.business.service.utils.SignupResponseEnum;
import com.cornerfoodmarketwebsite.business.service.utils.CountryAlpha2CodeEnum;
import com.cornerfoodmarketwebsite.business.service.utils.UuidUtil;
import com.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import com.cornerfoodmarketwebsite.data.single_table.repository.CustomerRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.DeliveryAddressRepository;
import com.cornerfoodmarketwebsite.data.single_table.entity.DeliveryAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class SignupService {

    private final HttpServletRequest httpServletRequest;

    private final CustomerRepository customerRepository;
    private final DeliveryAddressRepository deliveryAddressRepository;
    private final EmailService emailService;
    private final ExceptionLogService exceptionLogService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignupService(HttpServletRequest httpServletRequest, CustomerRepository customerRepository, DeliveryAddressRepository deliveryAddressRepository, EmailService emailService, ExceptionLogService exceptionLogService, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.httpServletRequest = httpServletRequest;
        this.customerRepository = customerRepository;
        this.deliveryAddressRepository = deliveryAddressRepository;
        this.emailService = emailService;
        this.exceptionLogService = exceptionLogService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public SignupResponseEnum processNewSignup(SignupForm signupForm) throws MessagingException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if (this.customerRepository.existsByEmail(signupForm.getEmail())) {
            return SignupResponseEnum.EXISTING_EMAIL;
        } else {
            SignupResponseEnum signupResponseEnum = this.saveSignupInformation(signupForm);
            if (signupResponseEnum == SignupResponseEnum.EMAIL_VERIFICATION_NEEDED) {
                this.sendEmailAddressVerificationEmail(signupForm);
            }
            return signupResponseEnum;
        }
    }

    private void sendEmailAddressVerificationEmail(SignupForm signupForm) throws UnsupportedEncodingException, NoSuchAlgorithmException, MessagingException {
        String requestUrl = this.httpServletRequest.getHeader("Access-Control-Allow-Origin");
        String digest = UuidUtil.getUuidDigest();
        this.customerRepository.setVerificationUuidByEmail(this.bCryptPasswordEncoder.encode(digest), signupForm.getEmail());
        this.emailService.sendEmail(signupForm.getEmail(), "Signup Email Verification", EmailTemplateCustomEnum.NEW_CUSTOMER_EMAIL_VERIFICATION_URL.getEmailContent(requestUrl, digest));
    }

    public void verifyEmailAddress() {

    }

    private SignupResponseEnum saveSignupInformation(SignupForm signupForm) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();        // TODO replace this encoder with spring security bean for customer.
        String encodedPassword = encoder.encode(signupForm.getPassword());
        Customer newCustomer = new Customer(signupForm.getEmail(), encodedPassword, signupForm.getFirstName(),
                signupForm.getMiddleName(), signupForm.getLastName(), signupForm.getCellPhoneNumber(), false, "", false, (short) 0);

        try {
            this.customerRepository.save(newCustomer);
        } catch (Exception exception) {
            this.exceptionLogService.logException(exception);
            return SignupResponseEnum.SERVER_ERROR;
        }

        if (signupForm.getIsAddressProvided()) {
            if (this.addDeliveryAddress(signupForm, this.customerRepository.getIdByEmail(newCustomer.getEmail()))) {
                return SignupResponseEnum.EMAIL_VERIFICATION_NEEDED;
            } else {
                return SignupResponseEnum.UNPROCESSED_DELIVERY_ADDRESS;
            }
        } else {
            return SignupResponseEnum.EMAIL_VERIFICATION_NEEDED;
        }
    }

    private boolean addDeliveryAddress(SignupForm signupForm, short newCustomerId) {
        DeliveryAddress newDeliveryAddress = new DeliveryAddress();

        newDeliveryAddress.setCustomerId(newCustomerId);

        newDeliveryAddress.setAddressLine1(signupForm.getAddressLine1());
        newDeliveryAddress.setAddressLine2(signupForm.getAddressLine2());
        newDeliveryAddress.setCity(signupForm.getCity());

        String countryAlpha2Code = signupForm.getCountryAlpha2Code();

        if (CountryAlpha2CodeEnum.valueOf(countryAlpha2Code) == CountryAlpha2CodeEnum.US) {
            newDeliveryAddress.setStateCode(signupForm.getStateCode());
        }
        newDeliveryAddress.setPostalCode(signupForm.getPostalCode());
        newDeliveryAddress.setCountryAlpha2Code(countryAlpha2Code);

        try {
            this.deliveryAddressRepository.save(newDeliveryAddress);
            return true;
        } catch (Exception exception) {
            this.exceptionLogService.logException(exception);
            return false;
        }
    }
}
