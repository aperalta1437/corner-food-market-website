package com.cornerfoodmarketwebsite.business.dto.response;

import com.cornerfoodmarketwebsite.data.single_table.entity.utils.AdministratorPermissionEnum;
import lombok.Getter;

import java.util.EnumSet;

@Getter
public class FinalAuthenticationResponse extends AuthenticationResponse {
    private final EnumSet<AdministratorPermissionEnum> permissions;

    public FinalAuthenticationResponse(EnumSet<AdministratorPermissionEnum> permissions, String accessToken, short userId, String name, String email) {
        super(accessToken, userId, name, email);
        this.permissions = permissions;
    }
}
