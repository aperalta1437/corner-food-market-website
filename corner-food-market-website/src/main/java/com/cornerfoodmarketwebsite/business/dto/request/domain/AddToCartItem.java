package com.cornerfoodmarketwebsite.business.dto.request.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddToCartItem {
    private byte quantity;
    private String sku;
}
