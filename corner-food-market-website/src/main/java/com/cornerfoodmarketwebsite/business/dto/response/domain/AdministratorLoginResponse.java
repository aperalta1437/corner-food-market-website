package com.cornerfoodmarketwebsite.business.dto.response.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdministratorLoginResponse {

    private final String jwt;
}
