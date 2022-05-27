package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.MessagingException;
import javax.swing.text.html.Option;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Optional;
import java.util.Random;

@Service
public class AdministratorLoginService {

    private final AdministratorRepository administratorRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public AdministratorLoginService(AdministratorRepository administratorRepository, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.administratorRepository = administratorRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public int sendTfaCodeAndGetExpirationTime(Administrator administrator) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException, MessagingException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();
        String tfaCode = this.generateTfaCodeByAdministratorId(administrator.getId());

        if (tfaTypeEnum == TfaTypeEnum.EMAIL) {
            this.emailService.sendEmail(administrator.getEmail(), "Two Factor Authentication code from our Service", EmailTemplateCustomEnum.TFA_CODE.getEmailContent(tfaCode));
        } else {    // TODO Implement SMS TfaTypeEnum
            if (tfaTypeEnum == null) {
                throw new NotProvidedTfaTypeException(ServiceExceptionInformationEnum.NOT_PROVIDED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            } else {
                throw new NotSupportedTfaTypeException(ServiceExceptionInformationEnum.NOT_SUPPORTED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            }
        }

        return RoleInformationEnum.ADMINISTRATOR.getTfaExpirationTimeInMilliseconds();
    }

    private String generateTfaCodeByAdministratorId(short administratorId) {
        Random rand = new Random();
        String tfaCode = String.format("%06d", rand.nextInt(999999));
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(tfaCode);

        this.administratorRepository.setTfaCodeDetailsById(encryptedTfaCode, new Timestamp(
                System.currentTimeMillis() + RoleInformationEnum.ADMINISTRATOR.getTfaExpirationTimeInMilliseconds()), administratorId);

        return tfaCode;
    }

    public boolean isCorrectTfaCodeByAdministrator(String tfaCode, short administratorId) {
        return this.bCryptPasswordEncoder.matches(tfaCode, this.administratorRepository.getTfaCodeById(administratorId));
    }

    public Optional<FirstFactorAuthenticationInformation> verifyCredentialsAndGetFirstFactorAuthenticationInformation(String email, String password) {
        Optional<Administrator> optionalAdministrator = this.administratorRepository.findByEmail(email);

        if (optionalAdministrator.isEmpty()) {
            return Optional.empty();
        } else {
            Administrator administrator = optionalAdministrator.get();
            if (this.bCryptPasswordEncoder.matches(password, administrator.getPassword())) {
                return Optional.empty();
            } else {
                return Optional.of(new FirstFactorAuthenticationInformation(administrator.getId(), administrator.isTfaEnabled()));
            }
        }
    }
}
