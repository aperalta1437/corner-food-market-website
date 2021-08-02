package com.psu.ist311.team5.cornerfoodmarketwebsite.business.service;

import com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain.ReviewInformation;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.GeneralReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.OrderReview;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewInformationService {

    private final GeneralReviewRepository generalReviewRepository;
    private final OrderReviewRepository orderReviewRepository;
    private final ItemReviewRepository itemReviewRepository;
    private final CustomerRepository customerRepository;
    private final OrderDetailsRepository orderDetailsRepository;

    @Autowired
    public ReviewInformationService(GeneralReviewRepository generalReviewRepository, OrderReviewRepository orderReviewRepository,
                                    ItemReviewRepository itemReviewRepository, CustomerRepository customerRepository,
                                    OrderDetailsRepository orderDetailsRepository) {
        this.generalReviewRepository = generalReviewRepository;
        this.orderReviewRepository = orderReviewRepository;
        this.itemReviewRepository = itemReviewRepository;
        this.customerRepository = customerRepository;
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<GeneralReview> getGeneralReviews() {

        List<GeneralReview> generalReviewList = this.generalReviewRepository.findAll();

        return generalReviewList;
    }

    public List<OrderReview> getOrderReviews() {

        List<OrderReview> orderReviewList = this.orderReviewRepository.findAll();

        return orderReviewList;
    }

    public List<ItemReview> getItemReviews() {

        List<ItemReview> itemReviewList = this.itemReviewRepository.findAll();

        return itemReviewList;
    }
}
