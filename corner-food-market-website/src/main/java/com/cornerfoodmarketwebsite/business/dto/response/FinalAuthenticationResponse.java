package com.cornerfoodmarketwebsite.business.dto.response;

import com.cornerfoodmarketwebsite.business.service.utils.TokenDetails;
import com.cornerfoodmarketwebsite.configuration.administrator.RefreshTokenProvider;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.AdministratorPermissionDomainEnum;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.AdministratorPermissionEnum;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.AdministratorPermissionTypeEnum;
import lombok.Getter;

import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;

@Getter
public class FinalAuthenticationResponse extends AuthenticationResponse {
    private final EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>> permissions;
    private final TokenDetails refreshTokenDetails;

    public FinalAuthenticationResponse(EnumMap<AdministratorPermissionDomainEnum, EnumMap<AdministratorPermissionTypeEnum, HashMap<String, Object>>> permissions, TokenDetails accessTokenDetails, short userId, String name, String email, TokenDetails refreshTokenDetails) {
        super(accessTokenDetails, userId, name, email);
        this.permissions = permissions;
        this.refreshTokenDetails = refreshTokenDetails;
    }
}
