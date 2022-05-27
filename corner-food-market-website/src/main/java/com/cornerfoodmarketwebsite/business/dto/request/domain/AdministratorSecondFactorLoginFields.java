package com.cornerfoodmarketwebsite.business.dto.request.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorSecondFactorLoginFields extends AdministratorFirstFactorLoginFields {
    private short id;
    private String tfaCode;
    private String loginAccessHash;
}
