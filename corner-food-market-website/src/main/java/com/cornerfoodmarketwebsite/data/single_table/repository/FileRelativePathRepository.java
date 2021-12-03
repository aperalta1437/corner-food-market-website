package com.cornerfoodmarketwebsite.data.single_table.repository;

import com.cornerfoodmarketwebsite.data.single_table.entity.FileRelativePath;
import com.cornerfoodmarketwebsite.data.utils.custom_jpa_repository.CustomJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRelativePathRepository extends CustomJpaRepository<FileRelativePath, Short> {
    boolean existsByRelativePath(String relativePath);

    @Query(value = "SELECT FRP1 FROM FileRelativePath FRP1 WHERE FRP1.relativePath = ?1")
    FileRelativePath getFileRelativePathByRelativePath(String relativePath);
}
