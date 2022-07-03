package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReAuthenticationResponse {
    private final String refreshToken;
    private final String accessToken;
}
