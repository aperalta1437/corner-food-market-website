package com.cornerfoodmarketwebsite.data.single_table.repository.implementation;


import com.cornerfoodmarketwebsite.data.single_table.entity.BannerImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BannerImageRepositoryImplementation {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BannerImage> getPageBannerImages(List<Integer> discountsIds) {
        return entityManager.createQuery("SELECT BI1 " +
                                            "FROM   BannerImage BI1 " +
                                            "ORDER BY   CASE " +
                                            "               WHEN (BI1.discountId IS NULL) THEN 3 " +
                                            "               WHEN (BI1.discountId NOT IN ?1) THEN 2 " +
                                            "               WHEN (BI1.discountId IN ?1) THEN 1 " +
                                            "           END", BannerImage.class)
                .setParameter(1, discountsIds).setMaxResults(10).getResultList();
    }
}
