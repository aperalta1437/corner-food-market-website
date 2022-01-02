package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.BannerImage;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BannerImageRepository extends CustomJpaRepository<BannerImage, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * " +
                    "FROM   (" +
                    "           SELECT  1 AS PRECEDENCE, BI1.* " +
                    "           FROM    BANNER_IMAGE BI1 " +
                    "           WHERE   DISCOUNT_ID IN ?1 " +
                    "           UNION ALL " +
                    "           SELECT  2 AS PRECEDENCE, BI1.* " +
                    "           FROM    BANNER_IMAGE BI1 " +
                    "           WHERE   (BI1.DISCOUNT_ID IS NULL) OR" +
                    "                   (BI1.DISCOUNT_ID NOT IN ?1) " +
                    "       ) BI0 " +
                    "ORDER BY " +
                    "       BI0.PRECEDENCE, BI0.DISCOUNT_ID IS NULL, BI0.SORT_NUMBER DESC " +
                    "LIMIT 10;")
    List<BannerImage> getPageBannerImages(List<Integer> discountsIds);
}
