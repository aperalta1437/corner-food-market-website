package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemDetailedInformationRepository extends JpaRepository<ItemDetailedInformation, Short> {
    @Query(value = "SELECT I1 FROM ItemDetailedInformation I1 WHERE I1.sku = ?1")
    ItemDetailedInformation findBySku(String itemSku);
}
