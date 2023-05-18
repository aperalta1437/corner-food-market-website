package com.cornerfoodmarketwebsite.configuration.utils;

import com.cornerfoodmarketwebsite.business.service.utils.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientOriginProperties {
    private String origin;
    private String storeName;
    private boolean allowed;
    private RoleEnum role;
    private JwtProperties jwt;
}
