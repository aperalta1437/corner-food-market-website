package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.ReviewInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.GeneralReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.ItemReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewInformationService {

    private final GeneralReviewRepository generalReviewRepository;
    private final OrderReviewRepository orderReviewRepository;
    private final ItemReviewRepository itemReviewRepository;
    private final CustomerRepository customerRepository;

    public ReviewInformationService(GeneralReviewRepository generalReviewRepository, OrderReviewRepository orderReviewRepository, ItemReviewRepository itemReviewRepository, CustomerRepository customerRepository) {
        this.generalReviewRepository = generalReviewRepository;
        this.orderReviewRepository = orderReviewRepository;
        this.itemReviewRepository = itemReviewRepository;
        this.customerRepository = customerRepository;
    }


    public List<ReviewInformation> getGeneralReviews() {

        Iterable<GeneralReview> generalReviews = this.generalReviewRepository.findAll();
        List<ReviewInformation> reviewInformationList = new ArrayList<>();

        generalReviews.forEach(generalReview -> {
            Short customerId = generalReview.getCustomerId();
            String customerFullName;
            if (customerId == null) {
                customerFullName = "Anonymous User";
            } else {
                customerFullName = this.customerRepository.getFullNameById(customerId);
            }

            ReviewInformation reviewInformation = new ReviewInformation(customerFullName, generalReview.getSubjectLine(),
                    generalReview.getReviewText(), generalReview.getStarRating(), generalReview.getCreatedAt());

            reviewInformationList.add(reviewInformation);
        });

        return reviewInformationList;
    }

    public List<ReviewInformation> getOrderReviews() {

        Iterable<GeneralReview> generalReviews = this.generalReviewRepository.findAll();
        List<ReviewInformation> reviewInformationList = new ArrayList<>();

        generalReviews.forEach(generalReview -> {
            Short customerId = generalReview.getCustomerId();
            String customerFullName;
            if (customerId == null) {
                customerFullName = "Anonymous User";
            } else {
                customerFullName = this.customerRepository.getFullNameById(customerId);
            }

            ReviewInformation reviewInformation = new ReviewInformation(customerFullName, generalReview.getSubjectLine(),
                    generalReview.getReviewText(), generalReview.getStarRating(), generalReview.getCreatedAt());

            reviewInformationList.add(reviewInformation);
        });

        return reviewInformationList;
    }
}
