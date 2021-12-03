package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.GeneralReview;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneralReviewRepository extends CustomJpaRepository<GeneralReview, Integer> {
}
