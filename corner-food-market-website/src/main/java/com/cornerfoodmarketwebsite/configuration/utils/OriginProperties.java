package com.cornerfoodmarketwebsite.configuration.utils;

import com.cornerfoodmarketwebsite.business.service.utils.RoleEnum;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class OriginProperties {
    @NonNull
    private final String origin;
    @NonNull
    private final String storeName;
    private final boolean isAllowed;
    @NonNull
    private final RoleEnum roleEnum;
}
