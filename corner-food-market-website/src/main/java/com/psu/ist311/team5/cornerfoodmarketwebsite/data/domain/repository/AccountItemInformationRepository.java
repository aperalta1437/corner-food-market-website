package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountItemInformationRepository extends JpaRepository<AccountItemInformation, Short> {
    @Query(value = "SELECT I1 FROM AccountItemInformation I1 LEFT JOIN I1.cartItems CI1 WHERE I1.isOnSale = true and I1.quantity > 0 and (CI1.customerId = ?1 or CI1.customerId = NULL)")
    Iterable<AccountItemInformation> findAllOnSale(short customerId);

    @Query(value = "SELECT I1 FROM AccountItemInformation I1 INNER JOIN I1.cartItems CI1 WHERE CI1.customerId = ?1")
    Iterable<AccountItemInformation> findAllInCart(short customerId);
}
