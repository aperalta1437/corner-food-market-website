package com.cornerfoodmarketwebsite.business.service;

import com.cornerfoodmarketwebsite.data.single_table.entity.ExceptionLog;
import com.cornerfoodmarketwebsite.data.single_table.repository.ExceptionLogRepository;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionLogService {
    private final ExceptionLogRepository exceptionLogRepository;

    @Autowired
    public ExceptionLogService(ExceptionLogRepository exceptionLogRepository) {
        this.exceptionLogRepository = exceptionLogRepository;
    }

    public void logException(Exception exception) {
        String stackTrace = ExceptionUtils.getStackTrace(exception);

        this.exceptionLogRepository.save(new ExceptionLog(stackTrace));
    }
}
