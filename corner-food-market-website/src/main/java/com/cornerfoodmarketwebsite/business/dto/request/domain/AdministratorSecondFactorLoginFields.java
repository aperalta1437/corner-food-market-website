package com.cornerfoodmarketwebsite.business.dto.request.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorSecondFactorLoginFields extends AdministratorFirstFactorLoginFields {
    private String tfaCode;
}
