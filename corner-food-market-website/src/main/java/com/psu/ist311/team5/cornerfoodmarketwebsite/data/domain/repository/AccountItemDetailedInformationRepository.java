package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.AccountItemDetailedInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountItemDetailedInformationRepository extends JpaRepository<AccountItemDetailedInformation, Short> {
    @Query(value = "SELECT I1 FROM AccountItemDetailedInformation I1 LEFT JOIN I1.cartItems CI1 WHERE I1.sku = ?1 and I1.isOnSale = true and I1.quantity > 0 and (CI1.customerId = ?2 or CI1.customerId = NULL)")
    AccountItemDetailedInformation findBySku(String itemSku, short customerId);
}
