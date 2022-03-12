package com.cornerfoodmarketwebsite.business.dto.response.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdministratorAddItemFormItemCategoryInformation {
    private final short id;
    private final String name;
    private final String description;
}
