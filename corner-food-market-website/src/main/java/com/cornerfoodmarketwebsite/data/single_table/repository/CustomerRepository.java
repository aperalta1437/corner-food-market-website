package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.Customer;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface CustomerRepository extends CustomJpaRepository<Customer, Short> {
    boolean existsByEmail(String email);

    @Query("SELECT C1 FROM Customer C1 WHERE C1.email = ?1")
    Customer findByEmail(String email);

    @Query("SELECT C1 FROM Customer C1 WHERE C1.id = ?1")
    Customer findById(short id);

    @Query("SELECT C1.id FROM Customer C1 WHERE C1.email = ?1")
    Short getIdByEmail(String email);

    @Query(value = "SELECT (CASE WHEN C1.MIDDLE_NAME IS NOT NULL OR C1.MIDDLE_NAME <> '' THEN CONCAT(C1.FIRST_NAME, ' ', C1.MIDDLE_NAME, ' ', C1.LAST_NAME) ELSE CONCAT(C1.FIRST_NAME, ' ', C1.LAST_NAME) END) FROM CUSTOMER C1 WHERE C1.ID = ?1", nativeQuery = true)
    String getFullNameById(short id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer SET verificationUuid = ?1 WHERE email = ?2")
    int setVerificationUuidByEmail(String verificationUuid, String email);
}
