package com.cornerfoodmarketwebsite.business.dto.response.domain;

import java.sql.Date;
import java.util.List;

public class OrderReviewInformation extends ReviewInformation{

    private List<String> orderItemNameList;
    private List<String> orderItemCategoryList;
    private List<String> orderItemSkuList;

    public OrderReviewInformation(String customerFullName, String subjectLine, String reviewText, float starRating,
                                  Date createdAt, List<String> orderItemNameList, List<String> orderItemCategoryList,
                                  List<String> orderItemSkuList) {
        super(customerFullName, subjectLine, reviewText, starRating, createdAt);

        this.orderItemNameList = orderItemNameList;
        this.orderItemCategoryList = orderItemCategoryList;
        this.orderItemSkuList = orderItemSkuList;
    }

    public List<String> getOrderItemNameList() {
        return orderItemNameList;
    }

    public void setOrderItemNameList(List<String> orderItemNameList) {
        this.orderItemNameList = orderItemNameList;
    }

    public List<String> getOrderItemCategoryList() {
        return orderItemCategoryList;
    }

    public void setOrderItemCategoryList(List<String> orderItemCategoryList) {
        this.orderItemCategoryList = orderItemCategoryList;
    }

    public List<String> getOrderItemSkuList() {
        return orderItemSkuList;
    }

    public void setOrderItemSkuList(List<String> orderItemSkuList) {
        this.orderItemSkuList = orderItemSkuList;
    }
}
