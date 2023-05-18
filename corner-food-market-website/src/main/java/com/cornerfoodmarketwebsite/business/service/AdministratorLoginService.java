package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.configuration.administrator.TfaAccessTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import com.cornerfoodmarketwebsite.exception.FailedFirstFactorAuthenticationRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private final TfaAccessTokenProvider tfaAccessTokenProvider;

    @Autowired
    public AdministratorLoginService(AdministratorRepository administratorRepository, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService, TfaAccessTokenProvider tfaAccessTokenProvider) {
        this.administratorRepository = administratorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
        this.tfaAccessTokenProvider = tfaAccessTokenProvider;
    }

    public TokenDetails sendTfaCodeAndGetDetailsForUser(Administrator administrator, int originNumber) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException, MessagingException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();

        Random rand = new Random();
        String tfaCode = String.format("%06d", rand.nextInt(999999));
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(tfaCode);

//        TfaCodeDetailsForUser tfaCodeDetailsForUser = new TfaCodeDetailsForUser(System.currentTimeMillis(), tfaCodeValidTimeframe);

        if (tfaTypeEnum == TfaTypeEnum.EMAIL) {
            this.emailService.sendEmail(administrator.getEmail(), "Two Factor Authentication code from our Service", EmailTemplateCustomEnum.TFA_CODE.getEmailContent(tfaCode));
        } else {    // TODO Implement SMS TfaTypeEnum
            if (tfaTypeEnum == null) {
                throw new NotProvidedTfaTypeException(ServiceExceptionInformationEnum.NOT_PROVIDED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            } else {
                throw new NotSupportedTfaTypeException(ServiceExceptionInformationEnum.NOT_SUPPORTED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            }
        }

        TokenDetails tfaAccessTokenDetails = tfaAccessTokenProvider.createToken(administrator.getEmail(), originNumber);

        this.administratorRepository.setTfaCodeDetailsById(encryptedTfaCode, administrator.getId());

        return tfaAccessTokenDetails;
    }

    public boolean isCorrectTfaCodeByAdministrator(String tfaCode, short administratorId) {
        return this.bCryptPasswordEncoder.matches(tfaCode, this.administratorRepository.getTfaCodeById(administratorId));
    }

    public FirstFactorAuthenticationInformation verifyCredentialsAndGetFirstFactorAuthenticationInformation(String email, String password) {
        Optional<Administrator> optionalAdministrator = this.administratorRepository.findByEmail(email);

        if (optionalAdministrator.isEmpty() || !this.bCryptPasswordEncoder.matches(password, optionalAdministrator.get().getPassword())) {
            throw new FailedFirstFactorAuthenticationRuntimeException();
        }
        return new FirstFactorAuthenticationInformation(optionalAdministrator.get().getId(), optionalAdministrator.get().isTfaEnabled());
    }
}
