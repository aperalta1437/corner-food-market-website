package com.cornerfoodmarketwebsite.business.dto.response;

import com.cornerfoodmarketwebsite.data.single_table.entity.utils.AdministratorPermissionEnum;
import lombok.Getter;

import java.util.HashSet;

@Getter
public class FinalAuthenticationResponse extends AuthenticationResponse {
    private final HashSet<AdministratorPermissionEnum> permissions;

    public FinalAuthenticationResponse(HashSet<AdministratorPermissionEnum> permissions, String accessToken, short userId, String name, String email) {
        super(accessToken, userId, name, email);
        this.permissions = permissions;
    }
}
