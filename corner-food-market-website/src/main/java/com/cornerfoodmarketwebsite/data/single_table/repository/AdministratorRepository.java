package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import com.cornerfoodmarketwebsite.data.single_table.entity.utils.TfaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Short> {
    boolean existsByEmail(String email);

    @Query("SELECT A1 FROM Administrator A1 WHERE A1.email = ?1")
    Administrator findByEmail(String email);

    @Query("SELECT A1 FROM Administrator A1 WHERE A1.id = ?1")
    Administrator findById(short id);

    @Query("SELECT A1.id FROM Administrator A1 WHERE A1.email = ?1")
    Short getIdByEmail(String email);

    @Query(value = "SELECT (CASE WHEN A1.MIDDLE_NAME IS NOT NULL OR A1.MIDDLE_NAME <> '' THEN CONCAT(A1.FIRST_NAME, ' ', A1.MIDDLE_NAME, ' ', A1.LAST_NAME) ELSE CONCAT(A1.FIRST_NAME, ' ', A1.LAST_NAME) END) FROM ADMINISTRATOR A1 WHERE A1.ID = ?1", nativeQuery = true)
    String getFullNameById(short id);

    @Query(value = "SELECT A1.tfaChosenType FROM Administrator A1 WHERE A1.id = ?1")
    TfaType getTfaChosenTypeById(short id);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Administrator SET tfaCode = ?1, tfaExpirationTime = ?2 WHERE id = ?3")
    int setTfaCodeDetailsById(String tfaCode, Timestamp tfaExpirationTime, short id);
}
