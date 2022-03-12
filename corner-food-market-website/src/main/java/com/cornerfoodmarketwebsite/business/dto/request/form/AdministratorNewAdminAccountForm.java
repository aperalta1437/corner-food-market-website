package com.cornerfoodmarketwebsite.business.dto.request.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdministratorNewAdminAccountForm {
    private final String email;
    private final String cellPhoneNumber;
}
