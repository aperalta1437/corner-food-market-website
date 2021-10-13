package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_IMAGE")
public class ItemImage {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ITEM_ID")
    private short itemId;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "SORT_NUMBER")
    private short sortNumber;

    @ManyToOne(targetEntity = FileRelativePath.class)
    @JoinColumn(name = "RELATIVE_PATH_ID")
    private FileRelativePath fileRelativePath;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public short getItemId() {
        return itemId;
    }

    public void setItemId(short itemId) {
        this.itemId = itemId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public short getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(short sortNumber) {
        this.sortNumber = sortNumber;
    }

    public short getRelativePathId() {
        return this.fileRelativePath.getId();
    }

    public FileRelativePath getFileRelativePath() {
        return fileRelativePath;
    }

    public void setFileRelativePath(FileRelativePath fileRelativePath) {
        this.fileRelativePath = fileRelativePath;
    }

    public String getSourceRelativePathName() {
        return this.fileRelativePath.getRelativePath() + this.fileName;
    }
}