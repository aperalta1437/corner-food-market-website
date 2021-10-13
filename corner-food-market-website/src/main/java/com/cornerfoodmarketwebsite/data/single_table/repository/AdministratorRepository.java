package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, Short> {
    boolean existsByEmail(String email);

    @Query("SELECT C1 FROM Administrator C1 WHERE C1.email = ?1")
    Administrator findByEmail(String email);

    @Query("SELECT C1 FROM Administrator C1 WHERE C1.id = ?1")
    Administrator findById(short id);

    @Query("SELECT C1.id FROM Administrator C1 WHERE C1.email = ?1")
    Short getIdByEmail(String email);

    @Query(value = "SELECT (CASE WHEN C1.MIDDLE_NAME IS NOT NULL OR C1.MIDDLE_NAME <> '' THEN CONCAT(C1.FIRST_NAME, ' ', C1.MIDDLE_NAME, ' ', C1.LAST_NAME) ELSE CONCAT(C1.FIRST_NAME, ' ', C1.LAST_NAME) END) FROM ADMINISTRATOR C1 WHERE C1.ID = ?1", nativeQuery = true)
    String getFullNameById(short id);
}
