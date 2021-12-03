package com.cornerfoodmarketwebsite.controller;

import com.cornerfoodmarketwebsite.business.dto.request.form.AnonymousGeneralReviewForm;
import com.cornerfoodmarketwebsite.business.service.ReviewInformationService;
import com.cornerfoodmarketwebsite.data.single_table.entity.GeneralReview;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemReview;
import com.cornerfoodmarketwebsite.data.single_table.entity.OrderReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/account/reviews")
public class AccountReviewsController {

    @Autowired
    private final ReviewInformationService reviewInformationService;

    public AccountReviewsController(ReviewInformationService reviewInformationService) {
        this.reviewInformationService = reviewInformationService;
    }

    @GetMapping
    public String getReviewsPage(Model model) {

        List<GeneralReview> generalReviewsList = this.reviewInformationService.getGeneralReviews();
        List<OrderReview> orderReviewList = this.reviewInformationService.getOrderReviews();
        List<ItemReview> itemReviewList = this.reviewInformationService.getItemReviews();

        model.addAttribute("generalReviewsList", generalReviewsList);
        model.addAttribute("orderReviewList", orderReviewList);
        model.addAttribute("itemReviewList", itemReviewList);
        model.addAttribute("anonymousGeneralReviewForm", new AnonymousGeneralReviewForm());
        return "account-reviews";
    }

    @PostMapping(value = "/process-general-review")
    public String processGeneralReview(AnonymousGeneralReviewForm anonymousGeneralReviewForm, Model model) {
        boolean isNewGeneralReviewSaved = this.reviewInformationService.saveAnonymousGeneralReview(anonymousGeneralReviewForm);

        model.addAttribute("isNewGeneralReviewSaved", isNewGeneralReviewSaved);

        return "fragments/review-submission-response::review-submission-response";
    }
}
