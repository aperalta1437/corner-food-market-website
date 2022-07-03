package com.cornerfoodmarketwebsite.configuration.utils;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtProperties {
    private TokenProperties tfaAccessToken;
    private TokenProperties accessToken;
    private TokenProperties refreshToken;
}
