package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.service.ItemInformationService;
import com.cornerfoodmarketwebsite.business.service.utils.ItemsInformationEnum;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

    private final ItemInformationService itemInformationService;


    @Autowired
    public HomeController(ItemInformationService itemInformationService) {
        this.itemInformationService = itemInformationService;
    }

    @GetMapping
    public Map<ItemsInformationEnum, List<? extends Serializable>> getPopularItemsInformation() {
        return this.itemInformationService.getPopularItemsInformation();
    }

    @GetMapping(value = "/results", params = {"items_search_query", "page"})
    public Map<ItemsInformationEnum, List<? extends Serializable>> getSearchResultsItemsInformation(@RequestParam(value = "items_search_query") String itemsSearchQuery, @RequestParam(value = "page") byte page) {
        return this.itemInformationService.getSearchResultsItemsInformation(itemsSearchQuery);
    }

    @GetMapping(value = "/categories/{item-category}", params = {"page"})
    public ItemDetailedInformation getCategoryItemsInformation(@PathVariable(value = "item-category") String itemCategory, @RequestParam("page") byte page) {
        return this.itemInformationService.getCategoryItemsInformation(itemCategory);
    }

    @GetMapping(value = "/categories/{item-category}/results", params = {"items_search_query", "page"})
    public ItemDetailedInformation getCategorySearchResultsItemsInformation(@PathVariable(value = "item-category") String itemCategory,
                                                              @RequestParam("items_search_query") String itemsSearchQuery, @RequestParam("page") byte page) {
        return this.itemInformationService.getCategorySearchResultsItemsInformation(itemCategory, itemsSearchQuery);
    }

    @GetMapping(value = "/categories/{item-category}/items/{item-sku}")
    public ItemDetailedInformation getItemDetailedInformation(@PathVariable(value = "item-category") String itemCategory,
                              @PathVariable(value = "item-sku") String itemSku) {
        return this.itemInformationService.getItemDetailedInformation(itemSku);
    }

//    @ModelAttribute
//    public void checkAuth(HttpServletResponse response) throws IOException {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if ((auth != null) && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
//            response.sendRedirect("/account");
//        }
//    }
}
