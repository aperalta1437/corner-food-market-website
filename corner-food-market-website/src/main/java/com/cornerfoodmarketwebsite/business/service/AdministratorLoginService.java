package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.utils.projection.TfaDetails;
import com.cornerfoodmarketwebsite.exception.ExpiredTfaCodeRuntimeException;
import com.cornerfoodmarketwebsite.exception.FailedFirstFactorAuthenticationRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Optional;
import java.util.Random;

@Service
public class AdministratorLoginService {
    private final AdministratorRepository administratorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Value(value = "${administrator.tfa-code.valid-timeframe}")
    private int tfaCodeValidTimeframe;
    @Value(value = "${administrator.tfa-code.validation-overhead-timeframe}")
    private int tfaCodeValidationOverheadTimeframe;

    @Autowired
    public AdministratorLoginService(AdministratorRepository administratorRepository, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.administratorRepository = administratorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public TfaCodeDetailsForUser sendTfaCodeAndGetDetailsForUser(Administrator administrator) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException, MessagingException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();

        Random rand = new Random();
        String tfaCode = String.format("%06d", rand.nextInt(999999));
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(tfaCode);

        TfaCodeDetailsForUser tfaCodeDetailsForUser = new TfaCodeDetailsForUser(System.currentTimeMillis(), tfaCodeValidTimeframe);
        this.administratorRepository.setTfaCodeDetailsById(encryptedTfaCode, tfaCodeDetailsForUser.getCreatedAt() + tfaCodeDetailsForUser.getValidTimeframe() + tfaCodeValidationOverheadTimeframe, administrator.getId());

        if (tfaTypeEnum == TfaTypeEnum.EMAIL) {
            this.emailService.sendEmail(administrator.getEmail(), "Two Factor Authentication code from our Service", EmailTemplateCustomEnum.TFA_CODE.getEmailContent(tfaCode));
        } else {    // TODO Implement SMS TfaTypeEnum
            if (tfaTypeEnum == null) {
                throw new NotProvidedTfaTypeException(ServiceExceptionInformationEnum.NOT_PROVIDED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            } else {
                throw new NotSupportedTfaTypeException(ServiceExceptionInformationEnum.NOT_SUPPORTED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            }
        }

        return tfaCodeDetailsForUser;
    }

    public boolean isCorrectTfaCodeByAdministrator(String tfaCode, short administratorId) {
        TfaDetails tfaDetails = this.administratorRepository.getTfaDetailsById(administratorId);

        if (System.currentTimeMillis() > tfaDetails.getTfaExpirationTime()) {
            throw new ExpiredTfaCodeRuntimeException();
        }

        return this.bCryptPasswordEncoder.matches(tfaCode, tfaDetails.getTfaCode());
    }

    public FirstFactorAuthenticationInformation verifyCredentialsAndGetFirstFactorAuthenticationInformation(String email, String password) {
        Optional<Administrator> optionalAdministrator = this.administratorRepository.findByEmail(email);

        if (optionalAdministrator.isEmpty() || !this.bCryptPasswordEncoder.matches(password, optionalAdministrator.get().getPassword())) {
            throw new FailedFirstFactorAuthenticationRuntimeException();
        }
        return new FirstFactorAuthenticationInformation(optionalAdministrator.get().getId(), optionalAdministrator.get().isTfaEnabled());
    }
}
