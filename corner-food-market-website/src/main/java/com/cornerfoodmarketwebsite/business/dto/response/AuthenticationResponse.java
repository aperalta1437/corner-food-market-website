package com.cornerfoodmarketwebsite.business.dto.response;

import com.cornerfoodmarketwebsite.business.service.utils.TokenDetails;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthenticationResponse {
    private final TokenDetails accessTokenDetails;
    private final short userId;
    private final String name;
    private final String email;
}
