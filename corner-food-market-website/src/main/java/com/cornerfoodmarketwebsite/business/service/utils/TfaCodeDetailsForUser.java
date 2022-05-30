package com.cornerfoodmarketwebsite.business.service.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class TfaCodeDetailsForUser {
    private final long createdAt;
    private final int validTimeframe;
}
