package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.domain.AddToCartItem;
import com.cornerfoodmarketwebsite.business.service.ItemInformationService;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    private final ItemInformationService itemInformationService;


    @Autowired
    public HomeController(ItemInformationService itemInformationService) {
        this.itemInformationService = itemInformationService;
    }

    @GetMapping
    public String getMainPage(Model model) {
        List<List<ItemInformation>> itemInformationLists = this.itemInformationService.getItemsInformation();

        model.addAttribute("itemInformationLists", itemInformationLists);
        model.addAttribute("addToCartItem", new AddToCartItem());
        return "home";
    }

    @GetMapping(value = "/categories/{item-category}/items/{item-sku}")
    public String getItemDetailsPage(@PathVariable(value = "item-category") String itemCategory,
                              @PathVariable(value = "item-sku") String itemSku, Model model) {

        ItemDetailedInformation itemDetailedInformation = this.itemInformationService.getItemDetailedInformation(itemSku);

        model.addAttribute("itemDetailedInformation", itemDetailedInformation);

        return "item-detail";
    }

    @ModelAttribute
    public void checkAuth(HttpServletResponse response) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            response.sendRedirect("/account");
        }
    }
}
