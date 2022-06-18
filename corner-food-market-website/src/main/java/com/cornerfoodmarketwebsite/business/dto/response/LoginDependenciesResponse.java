package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginDependenciesResponse {
    private final String loginBase64RsaPublicKey;
    private final long loginAccessCode;
}
