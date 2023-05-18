package com.cornerfoodmarketwebsite.business.dto.response;

import com.cornerfoodmarketwebsite.business.service.utils.TokenDetails;
import com.cornerfoodmarketwebsite.configuration.administrator.RefreshTokenProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReAuthenticationResponse {
    private final TokenDetails accessTokenDetails;
    private final TokenDetails refreshTokenDetails;
}
