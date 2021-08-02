package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity.ItemInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ItemInformationRepository extends JpaRepository<ItemInformation, Short> {
    @Query(value = "SELECT I1 FROM ItemInformation I1 WHERE I1.isOnSale = true")
    Iterable<ItemInformation> findAllOnSale();
}
