package com.cornerfoodmarketwebsite.business.service.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class FirstFactorAuthenticationInformation {
    private final short userId;
    private final boolean isTfaEnabled;
}
