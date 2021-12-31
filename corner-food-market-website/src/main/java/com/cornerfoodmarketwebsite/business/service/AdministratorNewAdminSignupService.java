package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.form.AdministratorNewAdminAccountForm;
import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.data.single_table.entity.NewAdministratorRequest;
import com.cornerfoodmarketwebsite.data.single_table.repository.NewAdministratorRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;

@Service
public class AdministratorNewAdminSignupService {

    private final HttpServletRequest httpServletRequest;

    private final NewAdministratorRequestRepository newAdministratorRequestRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public AdministratorNewAdminSignupService(HttpServletRequest httpServletRequest, NewAdministratorRequestRepository newAdministratorRequestRepository, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.httpServletRequest = httpServletRequest;
        this.newAdministratorRequestRepository = newAdministratorRequestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    /**
     * Validates the UUID used to sign up the new administrator.
     * @param newAdministratorEmail The new administrator email.
     * @param uuid The UUID to identify the new administrator signup.
     * @return The validation response of the UUID.
     */
    public UuidValidationResponseEnum validateUuid(String newAdministratorEmail, String uuid) {
        NewAdministratorRequest newAdministratorRequest = this.newAdministratorRequestRepository.getLatestNewAdministratorRequestByEmail(newAdministratorEmail);
        String encryptedUuid = this.bCryptPasswordEncoder.encode(uuid);

        if (newAdministratorRequest == null || !this.bCryptPasswordEncoder.matches(uuid, newAdministratorRequest.getUuid())) {
            return UuidValidationResponseEnum.NOT_FOUND;
        } else {
            if (!newAdministratorRequest.getExpirationDatetime().before(new Timestamp(
                    System.currentTimeMillis()))) {
                return UuidValidationResponseEnum.EXPIRED;
            } else if (newAdministratorRequest.getIsUsed()) {
                return UuidValidationResponseEnum.IS_USED;
            } else if (newAdministratorRequest.getIsCancelled()) {
                return UuidValidationResponseEnum.IS_CANCELLED;
            } else {
                return UuidValidationResponseEnum.FOUND;
            }
        }
    }

    public void generateNewAdministratorRequest(AdministratorNewAdminAccountForm administratorNewAdminAccountForm) throws NoSuchAlgorithmException, UnsupportedEncodingException, MessagingException {
        String digest = UuidUtil.getUuidDigest();
        NewAdministratorRequest newAdministratorRequest = new NewAdministratorRequest(administratorNewAdminAccountForm.getEmail(), administratorNewAdminAccountForm.getCellPhoneNumber(), this.bCryptPasswordEncoder.encode(digest),
                new Timestamp(System.currentTimeMillis() + UuidUsageEnum.NEW_ADMIN_REQUEST.getExpirationTimeInMilliseconds()), false, false);
        this.newAdministratorRequestRepository.save(newAdministratorRequest);

        String requestUrl = this.httpServletRequest.getHeader("Access-Control-Allow-Origin");
        this.emailService.sendEmail(administratorNewAdminAccountForm.getEmail(), "New Administrator Signup", EmailTemplateCustomEnum.NEW_ADMIN_SIGNUP_URL.getEmailContent(requestUrl, administratorNewAdminAccountForm.getEmail(), digest));
    }


}
