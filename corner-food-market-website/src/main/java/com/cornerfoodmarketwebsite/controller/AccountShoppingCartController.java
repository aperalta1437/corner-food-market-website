package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.AccountItemInformationService;
import com.cornerfoodmarketwebsite.data.domain.utils.ShoppingCartItemsList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/account/shopping-cart")
public class AccountShoppingCartController {

    private final AccountItemInformationService accountItemInformationService;

    @Autowired
    public AccountShoppingCartController(AccountItemInformationService accountItemInformationService) {
        this.accountItemInformationService = accountItemInformationService;
    }

    @GetMapping
    public String getShoppingCartPage(Model model) {
        ShoppingCartItemsList accountInCartItemInformationList = this.accountItemInformationService.getAllInCartItems();

        model.addAttribute("accountInCartItemInformationList", accountInCartItemInformationList);

        return "account-shopping-cart";
    }
}
