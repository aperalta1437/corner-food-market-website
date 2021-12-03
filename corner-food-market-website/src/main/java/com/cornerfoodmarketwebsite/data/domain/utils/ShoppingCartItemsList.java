package com.cornerfoodmarketwebsite.data.domain.utils;

import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;

import java.util.ArrayList;
import java.text.NumberFormat;

public class ShoppingCartItemsList extends ArrayList<AccountItemInformation> {
    private double totalPrice = 0;
    private double totalDiscountAmount = 0;
    private double totalPostDiscountPrice = 0;

    public String getFormattedTotalPrice() {
        return NumberFormat.getCurrencyInstance().format(totalPrice);
    }

    public String getFormattedTotalDiscountAmount() {
        return NumberFormat.getCurrencyInstance().format(totalDiscountAmount);
    }

    public String getFormattedTotalPostDiscountPrice() {
        return NumberFormat.getCurrencyInstance().format(totalPostDiscountPrice);
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public double getTotalPostDiscountPrice() {
        return totalPostDiscountPrice;
    }

    public void setTotalPostDiscountPrice(double totalPostDiscountPrice) {
        this.totalPostDiscountPrice = totalPostDiscountPrice;
    }

    public void setShoppingCartTotals() {
        // TODO Fix this method
//        this.forEach(accountItemInformation -> {
//            totalPrice += accountItemInformation.getPrice() * accountItemInformation.getInCartQuantity();
//            if (accountItemInformation.getIsPercentageBasedDiscount() != null) {
//                if (accountItemInformation.getIsPercentageBasedDiscount()) {
//                    totalDiscountAmount += accountItemInformation.getPrice() * (accountItemInformation.getDiscountPercent() / 100);
//                } else {
//                    totalDiscountAmount += accountItemInformation.getDiscountAmount();
//                }
//            }
//        });
//        totalPostDiscountPrice = totalPrice - totalDiscountAmount;
    }
}
