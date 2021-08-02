package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.OrderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
}
