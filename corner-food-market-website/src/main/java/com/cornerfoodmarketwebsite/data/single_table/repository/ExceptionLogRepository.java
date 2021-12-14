package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.ExceptionLog;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionLogRepository extends CustomJpaRepository<ExceptionLog, Long> {
}
