package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "EXCEPTION_LOG")
public class ExceptionLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "STACK_TRACE")
    private String stackTrace;

    public ExceptionLog(String stackTrace) {
        this.stackTrace = stackTrace;
    }
}
