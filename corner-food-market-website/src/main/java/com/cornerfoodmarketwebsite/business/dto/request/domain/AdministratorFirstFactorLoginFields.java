package com.cornerfoodmarketwebsite.business.dto.request.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdministratorFirstFactorLoginFields {
    private String email;
    private String encryptedPassword;
    private long loginAccessCode;
}
