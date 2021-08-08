package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.ItemInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {

    private final ItemInformationService itemInformationService;

    @Autowired
    public AccountController(ItemInformationService itemInformationService) {
        this.itemInformationService = itemInformationService;
    }

    @GetMapping(value = "/account")
    public String getAccountMainPage(Model model) throws IOException {

        List<List<ItemInformation>> itemInformationLists = this.itemInformationService.getItemsInformation();

        model.addAttribute("itemInformationLists", itemInformationLists);
        model.addAttribute("addToCartItem", new AddToCartItem());

        return "account-home";
    }
}
