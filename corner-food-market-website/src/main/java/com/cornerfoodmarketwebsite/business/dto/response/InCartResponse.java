package com.cornerfoodmarketwebsite.business.dto.response;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class InCartResponse {
    @NonNull
    private String addToCartResponse;
    @NonNull
    private short cartRequestedItemTotal;
    @NonNull
    private short cartTotalItems;
    @NonNull
    private String requestedItemUpc;
    @NonNull
    private boolean isSuccess;
}
