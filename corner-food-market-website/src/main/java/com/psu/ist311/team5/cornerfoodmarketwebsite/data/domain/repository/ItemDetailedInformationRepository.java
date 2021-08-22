package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailedInformationRepository extends JpaRepository<ItemDetailedInformation, Short> {
    @Query(value = "SELECT I1 FROM ItemDetailedInformation I1 WHERE I1.sku = ?1 and I1.isOnSale = true and I1.quantity > 0")
    ItemDetailedInformation findBySku(String itemSku);
}
