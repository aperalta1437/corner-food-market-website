package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.*;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import com.cornerfoodmarketwebsite.data.single_table.repository.utils.projection.TfaDetails;
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

    public TfaCodeDetailsForUser sendTfaCodeAndGetDetailsForUser(Administrator administrator) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException, MessagingException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();

        Random rand = new Random();
        String tfaCode = String.format("%06d", rand.nextInt(999999));
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(tfaCode);

        TfaCodeDetailsForUser tfaCodeDetailsForUser = new TfaCodeDetailsForUser(System.currentTimeMillis(), RoleInformationEnum.ADMINISTRATOR.getTfaCodeValidTimeframe());
        this.administratorRepository.setTfaCodeDetailsById(encryptedTfaCode, tfaCodeDetailsForUser.getCreatedAt() + tfaCodeDetailsForUser.getValidTimeframe(), administrator.getId());

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

    public boolean isCorrectTfaCodeByAdministrator(String tfaCode, short administratorId) throws ExpiredTfaCodeException {
        TfaDetails tfaDetails = this.administratorRepository.getTfaDetailsById(administratorId);

        if (System.currentTimeMillis() > tfaDetails.getTfaExpirationTime()) {
            throw new ExpiredTfaCodeException();
        }

        return this.bCryptPasswordEncoder.matches(tfaCode, tfaDetails.getTfaCode());
    }

    public Optional<FirstFactorAuthenticationInformation> verifyCredentialsAndGetFirstFactorAuthenticationInformation(String email, String password) {
        Optional<Administrator> optionalAdministrator = this.administratorRepository.findByEmail(email);

        if (optionalAdministrator.isEmpty()) {
            return Optional.empty();
        } else {
            Administrator administrator = optionalAdministrator.get();
            if (this.bCryptPasswordEncoder.matches(password, administrator.getPassword())) {
                return Optional.of(new FirstFactorAuthenticationInformation(administrator.getId(), administrator.isTfaEnabled()));
            } else {
                return Optional.empty();
            }
        }
    }
}
