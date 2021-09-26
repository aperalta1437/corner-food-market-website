package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.cornerfoodmarketwebsite.business.service.AccountItemInformationService;
import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import com.cornerfoodmarketwebsite.business.dto.response.InCartResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/account")
public class AccountHomeController {

    private final AccountItemInformationService accountItemInformationService;

    @Autowired
    public AccountHomeController(AccountItemInformationService accountItemInformationService) {
        this.accountItemInformationService = accountItemInformationService;
    }

    @GetMapping
    public String getAccountMainPage(Model model, HttpServletRequest request) throws IOException {
        InCartResponse inCartResponse = (InCartResponse) request.getAttribute("inCartResponse");
        if (inCartResponse != null) {
            System.out.println(inCartResponse.getAddToCartResponse());
        }

        List<List<AccountItemInformation>> accountItemInformationLists = this.accountItemInformationService.getItemsInformation();

        model.addAttribute("accountItemInformationLists", accountItemInformationLists);
        model.addAttribute("addToCartItem", new AddToCartItem());

        return "account-home";
    }

    @GetMapping(value = "/categories/{item-category}/items/{item-sku}")
    public String getItemDetailsPage(@PathVariable(value = "item-category") String itemCategory,
                                     @PathVariable(value = "item-sku") String itemSku, Model model) {

        AccountItemDetailedInformation accountItemDetailedInformation = this.accountItemInformationService.getItemDetailedInformation(itemSku);

        model.addAttribute("accountItemDetailedInformation", accountItemDetailedInformation);

        return "account-item-detail";
    }

    @GetMapping(value = "/add-to-customer-cart/{item-sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addItemToCustomerCart(@PathVariable(value = "item-sku") String itemSku, AddToCartItem addToCartItem,
                                      HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo make sure this route goes to 404 if addToCartItem is not provided

        addToCartItem.setSku(itemSku);
        request.setAttribute("inCartResponse", this.accountItemInformationService.addItemToCart(addToCartItem));

        System.out.println(request.getAttribute("inCartResponse"));
        System.out.println(addToCartItem.getQuantity());
        request.getRequestDispatcher("/account").forward(request, response);
    }

    @PostMapping(value = "/add-to-cart/{item-sku}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    InCartResponse addItemToCart(@PathVariable(value = "item-sku") String itemSku, AddToCartItem addToCartItem) {
        addToCartItem.setSku(itemSku);

        return this.accountItemInformationService.addItemToCart(addToCartItem);
    }
}
