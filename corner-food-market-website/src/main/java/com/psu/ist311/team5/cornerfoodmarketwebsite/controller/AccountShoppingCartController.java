package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.AccountItemInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AccountShoppingCartController {

    private final AccountItemInformationService accountItemInformationService;

    @Autowired
    public AccountShoppingCartController(AccountItemInformationService accountItemInformationService) {
        this.accountItemInformationService = accountItemInformationService;
    }

    @GetMapping(value = "/account/shopping-cart")
    public String getShoppingCartPage(Model model) {
        List<AccountItemInformation> accountInCartItemInformationList = this.accountItemInformationService.getAllInCartItems();

        model.addAttribute("accountInCartItemInformationList", accountInCartItemInformationList);

        return "account-shopping-cart";
    }
}
