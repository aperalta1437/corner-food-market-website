package com.cornerfoodmarketwebsite.business.service.utils;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Base64PublicKeyInformation {
    @NonNull
    private final String base64PublicKey;
    private final long keysMapAccessNumber;
}
