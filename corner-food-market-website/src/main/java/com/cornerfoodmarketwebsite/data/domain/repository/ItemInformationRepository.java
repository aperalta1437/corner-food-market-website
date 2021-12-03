package com.cornerfoodmarketwebsite.data.domain.repository;

import com.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemInformationRepository extends CustomJpaRepository<ItemInformation, Short> {
    @Query(value = "SELECT I1 FROM ItemInformation I1 WHERE I1.isOnSale = true and I1.quantity > 0")
    Iterable<ItemInformation> findAllOnSale();
}
