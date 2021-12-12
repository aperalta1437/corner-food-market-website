package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "FILE_RELATIVE_PATH")
public class FileRelativePath {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "FILE_TYPE_ID")
    private short fileTypeId;
    @Column(name = "RELATIVE_PATH")
    private String relativePath;

    public FileRelativePath() {
    }

    public FileRelativePath(short fileTypeId, String relativePath) {
        this.fileTypeId = fileTypeId;
        this.relativePath = relativePath;
    }

    public short getId() {
        return id;
    }

    public short getFileTypeId() {
        return fileTypeId;
    }

    public void setFileTypeId(short fileTypeId) {
        this.fileTypeId = fileTypeId;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }
}