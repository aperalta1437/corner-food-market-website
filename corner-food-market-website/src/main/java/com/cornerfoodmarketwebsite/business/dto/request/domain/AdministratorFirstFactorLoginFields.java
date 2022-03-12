package com.cornerfoodmarketwebsite.business.dto.request.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdministratorFirstFactorLoginFields {
    private String email;
    private String password;
}
