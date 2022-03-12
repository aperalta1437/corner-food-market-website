package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EXCEPTION_LOG")
@RequiredArgsConstructor
public class ExceptionLog {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "STACK_TRACE")
    private final String stackTrace;
}
