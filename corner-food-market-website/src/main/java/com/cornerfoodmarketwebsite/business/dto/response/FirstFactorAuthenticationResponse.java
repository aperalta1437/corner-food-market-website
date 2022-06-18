package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;

@Getter
public class FirstFactorAuthenticationResponse extends AuthenticationResponse {
    private final int tfaCodeValidTimeframe;
    private final long tfaCodeCreatedAt;

    public FirstFactorAuthenticationResponse(int tfaCodeValidTimeframe, long tfaCodeCreatedAt,  String accessToken, short userId, String name, String email) {
        super(accessToken, userId, name, email);
        this.tfaCodeValidTimeframe = tfaCodeValidTimeframe;
        this.tfaCodeCreatedAt = tfaCodeCreatedAt;
    }
}
