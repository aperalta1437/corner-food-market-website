package com.cornerfoodmarketwebsite.data.domain.repository;

import com.cornerfoodmarketwebsite.data.domain.entity.ItemDetailedInformation;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailedInformationRepository extends CustomJpaRepository<ItemDetailedInformation, Short> {
    @Query(value = "SELECT I1 FROM ItemDetailedInformation I1 WHERE I1.upc = ?1 and I1.isOnSale = true and I1.quantity > 0")
    ItemDetailedInformation findByUpc(String itemUpc);
}
