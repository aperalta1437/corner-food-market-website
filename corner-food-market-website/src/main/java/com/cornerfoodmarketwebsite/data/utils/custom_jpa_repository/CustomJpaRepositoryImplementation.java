package com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository;

import org.hibernate.Session;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import java.io.Serializable;

public class CustomJpaRepositoryImplementation<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements CustomJpaRepository<T, ID> {

    private final EntityManager entityManager;

    public CustomJpaRepositoryImplementation(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void refresh(T t) {
//        entityManager.persist(t);
//        entityManager.flush();
        entityManager.refresh(t);
    }
}