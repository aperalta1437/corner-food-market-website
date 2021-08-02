package com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Short> {
    boolean existsByEmail(String email);

    @Query("SELECT C1 FROM Customer C1 WHERE C1.email = ?1")
    Customer findByEmail(String email);

    @Query("SELECT C1 FROM Customer C1 WHERE C1.id = ?1")
    Customer findById(short id);

    @Query("SELECT C1.id FROM Customer C1 WHERE C1.email = ?1")
    Short getIdByEmail(String email);

    @Query(value = "SELECT (CASE WHEN C1.MIDDLE_NAME IS NOT NULL OR C1.MIDDLE_NAME <> '' THEN CONCAT(C1.FIRST_NAME, ' ', C1.MIDDLE_NAME, ' ', C1.LAST_NAME) ELSE CONCAT(C1.FIRST_NAME, ' ', C1.LAST_NAME) END) FROM CUSTOMER C1 WHERE C1.ID = ?1", nativeQuery = true)
    String getFullNameById(short id);
}
