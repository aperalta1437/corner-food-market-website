package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Integer> {
}