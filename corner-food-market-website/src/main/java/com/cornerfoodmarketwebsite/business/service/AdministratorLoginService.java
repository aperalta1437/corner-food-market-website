package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.service.utils.NotProvidedTfaTypeException;
import com.cornerfoodmarketwebsite.business.service.utils.NotSupportedTfaTypeException;
import com.cornerfoodmarketwebsite.business.service.utils.RoleInformationEnum;
import com.cornerfoodmarketwebsite.business.service.utils.ServiceExceptionInformationEnum;
import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaTypeEnum;
import com.cornerfoodmarketwebsite.data.single_table.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public AdministratorLoginService(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    public int sendTfaCodeAndGetExpirationTime(Administrator administrator) throws NotProvidedTfaTypeException, NotSupportedTfaTypeException {
        TfaTypeEnum tfaTypeEnum = administrator.getTfaChosenType();
        int tfaCode = this.generateTfaCodeForAdministrator(administrator.getId());

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

    private int generateTfaCodeForAdministrator(short administratorId) {
        Random rand = new Random();
        int tfaCode = rand.nextInt(899999) + 100000;
        String encodedTfaCode = this.bCryptPasswordEncoder.encode(String.valueOf(tfaCode));

        this.administratorRepository.setTfaCodeDetailsById(encodedTfaCode, new Timestamp(
                System.currentTimeMillis() + RoleInformationEnum.ADMINISTRATOR.getTfaExpirationTimeInMilliseconds()), administratorId);

        return tfaCode;
    }
}
