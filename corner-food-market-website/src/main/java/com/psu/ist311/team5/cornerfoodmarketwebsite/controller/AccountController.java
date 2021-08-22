package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.InCartResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.utils.AddToCartResponse;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.AccountItemInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.ItemInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

@Controller
public class AccountController {

    private final AccountItemInformationService accountItemInformationService;

    @Autowired
    public AccountController(AccountItemInformationService accountItemInformationService) {
        this.accountItemInformationService = accountItemInformationService;
    }

    @GetMapping(value = "/account")
    public String getAccountMainPage(Model model, HttpServletRequest request) throws IOException {
        InCartResponse inCartResponse = (InCartResponse) request.getAttribute("inCartResponse");
        System.out.println(inCartResponse);
        if (inCartResponse != null) {
            System.out.println(inCartResponse.getAddToCartResponse());
        }

        List<List<AccountItemInformation>> accountItemInformationLists = this.accountItemInformationService.getItemsInformation();

        model.addAttribute("accountItemInformationLists", accountItemInformationLists);
        model.addAttribute("addToCartItem", new AddToCartItem());

        return "account-home";
    }

    @GetMapping(value = "/account/add-to-customer-cart/{item-sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCustomerCart(@PathVariable(value = "item-sku") String itemSku, AddToCartItem addToCartItem,
                                      HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo make sure this route goes to 404 if addToCartItem is not provided

        addToCartItem.setSku(itemSku);
        request.setAttribute("inCartResponse", this.accountItemInformationService.addItemToCart(addToCartItem));

        System.out.println(request.getAttribute("inCartResponse"));
        System.out.println(addToCartItem.getQuantity());
        request.getRequestDispatcher("/account").forward(request, response);
    }

    @PostMapping(value = "/account/add-to-cart/{item-sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InCartResponse addItemToCart(@PathVariable(value = "item-sku") String itemSku, AddToCartItem addToCartItem) {
        addToCartItem.setSku(itemSku);

        return this.accountItemInformationService.addItemToCart(addToCartItem);
    }
}
