package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponse {
    private final String accessToken;
    private final short userId;
    private final String name;
    private final String email;
}
