package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.ReviewInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.ItemInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.ReviewInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.GeneralReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.OrderReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController {

    private final ItemInformationService itemInformationService;


    @Autowired
    public HomeController(ItemInformationService itemInformationService) {
        this.itemInformationService = itemInformationService;
    }

    @GetMapping(value = "/")
    public String getMainPage(Model model) {
        List<List<ItemInformation>> itemInformationLists = this.itemInformationService.getItemsInformation();

        model.addAttribute("itemInformationLists", itemInformationLists);
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
