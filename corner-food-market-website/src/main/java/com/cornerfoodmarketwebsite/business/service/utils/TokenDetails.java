package com.cornerfoodmarketwebsite.business.service.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TokenDetails {
    private final String token;
    private final long validTimeframe;
    private final long createdAt;
}
