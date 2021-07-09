package com.psu.ist311.team5.cornerfoodmarketwebsite.data.repository;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity.ItemCategoryHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCategoryHierarchyRepository extends JpaRepository<ItemCategoryHierarchy, Short> {
}
