package com.psu.ist311.team5.cornerfoodmarketwebsite.controller;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.request.form.AnonymousGeneralReviewForm;
import com.psu.ist311.team5.cornerfoodmarketwebsite.business.service.ReviewInformationService;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.GeneralReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.OrderReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ReviewsController {

    @Autowired
    private final ReviewInformationService reviewInformationService;

    public ReviewsController(ReviewInformationService reviewInformationService) {
        this.reviewInformationService = reviewInformationService;
    }

    @GetMapping(value = "/reviews")
    public String getReviewsPage(Model model) {

        List<GeneralReview> generalReviewsList = this.reviewInformationService.getGeneralReviews();
        List<OrderReview> orderReviewList = this.reviewInformationService.getOrderReviews();
        List<ItemReview> itemReviewList = this.reviewInformationService.getItemReviews();

        model.addAttribute("generalReviewsList", generalReviewsList);
        model.addAttribute("orderReviewList", orderReviewList);
        model.addAttribute("itemReviewList", itemReviewList);
        model.addAttribute("anonymousGeneralReviewForm", new AnonymousGeneralReviewForm());
        return "reviews";
    }

    @PostMapping(value = "/reviews/process-general-review")
    public String processGeneralReview(AnonymousGeneralReviewForm anonymousGeneralReviewForm, Model model) {
        boolean isNewGeneralReviewSaved = this.reviewInformationService.saveAnonymousGeneralReview(anonymousGeneralReviewForm);

        model.addAttribute("isNewGeneralReviewSaved", isNewGeneralReviewSaved);

        return "fragments/review-submission-response::review-submission-response";
    }
}
