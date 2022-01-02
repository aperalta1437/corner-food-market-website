package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BANNER_IMAGE")
public class BannerImage implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "DISCOUNT_ID")
    private Integer discountId;
    @Column(name = "FILE_EXTENSION")
    private String fileExtension;
    @Column(name = "FILE_NAME")
    private String fileName;
    @Column(name = "SORT_NUMBER")
    private short sortNumber;
    @Column(name = "HAS_TEXT_OVERLAY")
    private boolean hasTextOverlay;
    @Column(name = "TEXT_OVERLAY_1")
    private String textOverlay1;
    @Column(name = "TEXT_OVERLAY_1_CSS_LEFT_VALUE")
    private String textOverlay1CssLeftValue;
    @Column(name = "TEXT_OVERLAY_1_CSS_TOP_VALUE")
    private String textOverlay1CssTopValue;
    @Column(name = "TEXT_OVERLAY_2")
    private String textOverlay2;
    @Column(name = "TEXT_OVERLAY_2_CSS_LEFT_VALUE")
    private String textOverlay2CssLeftValue;
    @Column(name = "TEXT_OVERLAY_2_CSS_TOP_VALUE")
    private String textOverlay2CssTopValue;
    @Column(name = "IS_DISABLED")
    private boolean isDisabled;
    @Column(name = "IS_DELETED")
    private boolean isDeleted;

    @ManyToOne(targetEntity = FileRelativePath.class)
    @JoinColumn(name = "FILE_RELATIVE_PATH_ID")
    private FileRelativePath fileRelativePath;

    public BannerImage() {
    }

    public BannerImage(Integer discountId, String fileExtension, short sortNumber, boolean hasTextOverlay, String textOverlay1, String textOverlay1CssLeftValue, String textOverlay1CssTopValue, String textOverlay2, String textOverlay2CssLeftValue, String textOverlay2CssTopValue, boolean isDisabled, boolean isDeleted, FileRelativePath fileRelativePath) {
        this.discountId = discountId;
        this.fileExtension = fileExtension;
        this.sortNumber = sortNumber;
        this.hasTextOverlay = hasTextOverlay;
        this.textOverlay1 = textOverlay1;
        this.textOverlay1CssLeftValue = textOverlay1CssLeftValue;
        this.textOverlay1CssTopValue = textOverlay1CssTopValue;
        this.textOverlay2 = textOverlay2;
        this.textOverlay2CssLeftValue = textOverlay2CssLeftValue;
        this.textOverlay2CssTopValue = textOverlay2CssTopValue;
        this.isDisabled = isDisabled;
        this.isDeleted = isDeleted;
        this.fileRelativePath = fileRelativePath;
    }

    public int getId() {
        return id;
    }

    public Integer getDiscountId() {
        return discountId;
    }

    public void setDiscountId(Integer discountId) {
        this.discountId = discountId;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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

    public boolean getHasTextOverlay() {
        return hasTextOverlay;
    }

    public void setHasTextOverlay(boolean hasTextOverlay) {
        this.hasTextOverlay = hasTextOverlay;
    }

    public String getTextOverlay1() {
        return textOverlay1;
    }

    public void setTextOverlay1(String textOverlay1) {
        this.textOverlay1 = textOverlay1;
    }

    public String getTextOverlay1CssLeftValue() {
        return textOverlay1CssLeftValue;
    }

    public void setTextOverlay1CssLeftValue(String textOverlay1CssLeftValue) {
        this.textOverlay1CssLeftValue = textOverlay1CssLeftValue;
    }

    public String getTextOverlay1CssTopValue() {
        return textOverlay1CssTopValue;
    }

    public void setTextOverlay1CssTopValue(String textOverlay1CssTopValue) {
        this.textOverlay1CssTopValue = textOverlay1CssTopValue;
    }

    public String getTextOverlay2() {
        return textOverlay2;
    }

    public void setTextOverlay2(String textOverlay2) {
        this.textOverlay2 = textOverlay2;
    }

    public String getTextOverlay2CssLeftValue() {
        return textOverlay2CssLeftValue;
    }

    public void setTextOverlay2CssLeftValue(String textOverlay2CssLeftValue) {
        this.textOverlay2CssLeftValue = textOverlay2CssLeftValue;
    }

    public String getTextOverlay2CssTopValue() {
        return textOverlay2CssTopValue;
    }

    public void setTextOverlay2CssTopValue(String textOverlay2CssTopValue) {
        this.textOverlay2CssTopValue = textOverlay2CssTopValue;
    }

    public boolean getIsDisabled() {
        return isDisabled;
    }

    public void setIsDisabled(boolean isDisabled) {
        this.isDisabled = isDisabled;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
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

    public String generateNewFileName() {
        return this.id + "." + this.fileExtension;
    }
}
