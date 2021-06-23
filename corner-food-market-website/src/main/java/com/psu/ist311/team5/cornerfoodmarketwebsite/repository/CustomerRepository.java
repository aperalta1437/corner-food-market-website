package com.psu.ist311.team5.cornerfoodmarketwebsite.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short> {
}
