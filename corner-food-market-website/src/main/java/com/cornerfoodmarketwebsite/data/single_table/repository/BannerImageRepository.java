package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.BannerImage;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerImageRepository extends CustomJpaRepository<BannerImage, Integer> {
}
