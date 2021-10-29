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
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.sql.Timestamp;
import java.util.Random;

@Service
public class AdministratorLoginService {

    private final AdministratorRepository administratorRepository;


    @Qualifier(value = "administratorPreTfaPasswordEncoder")
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RsaUtil rsaUtil;

    @Autowired
    public AdministratorLoginService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public int sendTfaCodeAndGetExpirationTime(Administrator administrator) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();
        int tfaCode = this.generateTfaCodeByAdministratorId(administrator.getId());

        if (tfaTypeEnum == TfaTypeEnum.EMAIL) {
            this.emailService.sendTfaCodeEmail(administrator.getEmail(), tfaCode);
        } else {    // TODO Implement SMS TfaTypeEnum
            if (tfaTypeEnum == null) {
                throw new NotProvidedTfaTypeException(ServiceExceptionInformationEnum.NOT_PROVIDED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            } else {
                throw new NotSupportedTfaTypeException(ServiceExceptionInformationEnum.NOT_SUPPORTED_TFA_TYPE_EXCEPTION.getExceptionMessage());
            }
        }

        return RoleInformationEnum.ADMINISTRATOR.getTfaExpirationTimeInMilliseconds();
    }

    private int generateTfaCodeByAdministratorId(short administratorId) {
        Random rand = new Random();
        int tfaCode = rand.nextInt(899999) + 100000;
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(String.valueOf(tfaCode));

        this.administratorRepository.setTfaCodeDetailsById(encryptedTfaCode, new Timestamp(
                System.currentTimeMillis() + RoleInformationEnum.ADMINISTRATOR.getTfaExpirationTimeInMilliseconds()), administratorId);

        return tfaCode;
    }

    public String getBase64RsaPublicKeyByAdministratorId(short administratorId) {
        Base64RsaKeyPair base64RsaKeyPair = this.rsaUtil.generateBase64RsaKeyPair();
        System.out.println(base64RsaKeyPair.getBase64PrivateKey());
        System.out.println(base64RsaKeyPair.getBase64PrivateKey().length());
        this.administratorRepository.setRsaPrivateKeyById(base64RsaKeyPair.getBase64PrivateKey(), administratorId);
        return base64RsaKeyPair.getBase64PublicKey();
    }

    public String decryptTextByAdministrator(String base64EncryptedText, short adminitratorId) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, InvalidKeySpecException, BadPaddingException, InvalidKeyException {
        return this.rsaUtil.decrypt(base64EncryptedText, this.administratorRepository.getRsaPrivateKeyById(adminitratorId));
    }

    public boolean isCorrectTfaCodeByAdministrator(String tfaCode, short administratorId) {
        String encryptedTfaCode = this.bCryptPasswordEncoder.encode(tfaCode);

        return (encryptedTfaCode.equals(this.administratorRepository.getTfaCodeById(administratorId)));
    }

    public boolean isCorrectCredentials(String email, String password) {
        return this.bCryptPasswordEncoder.matches(password, this.administratorRepository.getPasswordByEmail(email));
    }
}
