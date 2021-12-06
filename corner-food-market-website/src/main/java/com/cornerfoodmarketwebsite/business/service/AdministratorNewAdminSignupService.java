package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.business.dto.request.form.AdministratorNewAdminAccountForm;
import com.cornerfoodmarketwebsite.business.service.utils.HelperMethods;
import com.cornerfoodmarketwebsite.business.service.utils.UuidUsageEnum;
import com.cornerfoodmarketwebsite.business.service.utils.UuidValidationResponseEnum;
import com.cornerfoodmarketwebsite.data.single_table.entity.NewAdministratorRequest;
import com.cornerfoodmarketwebsite.data.single_table.repository.NewAdministratorRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.UUID;

@Service
public class AdministratorNewAdminSignupService {
    private final NewAdministratorRequestRepository newAdministratorRequestRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    @Autowired
    public AdministratorNewAdminSignupService(NewAdministratorRequestRepository newAdministratorRequestRepository, @Qualifier(value = "administratorPreTfaPasswordEncoder") BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.newAdministratorRequestRepository = newAdministratorRequestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    public UuidValidationResponseEnum validateUuid(String uuid) {
        if (this.newAdministratorRequestRepository.existsByUuid(uuid)) {
            NewAdministratorRequest newAdministratorRequest = this.newAdministratorRequestRepository.getNewAdministratorRequestByUuid(uuid);
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
        } else {
            return UuidValidationResponseEnum.NOT_FOUND;
        }
    }

    public void generateNewAdministratorRequest(AdministratorNewAdminAccountForm administratorNewAdminAccountForm) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest salt = MessageDigest.getInstance("SHA-256");
        salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
        String digest = HelperMethods.bytesToHex(salt.digest());
        NewAdministratorRequest newAdministratorRequest = new NewAdministratorRequest(administratorNewAdminAccountForm.getEmail(), administratorNewAdminAccountForm.getCellPhoneNumber(), digest,
                new Timestamp(System.currentTimeMillis() + UuidUsageEnum.NEW_ADMIN_REQUEST.getExpirationTimeInMilliseconds()), false, false);
        this.newAdministratorRequestRepository.save(newAdministratorRequest);

        this.emailService.sendNewAdminSignupUrl(administratorNewAdminAccountForm.getEmail(), digest);
    }


}
