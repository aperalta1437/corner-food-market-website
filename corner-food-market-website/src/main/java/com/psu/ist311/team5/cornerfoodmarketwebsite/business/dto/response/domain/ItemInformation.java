package com.psu.ist311.team5.cornerfoodmarketwebsite.business.dto.response.domain;

public class ItemInformation {

    private String name;
    private String sku;
    private String price;
    private boolean isPopular;
    private Boolean isPercentageBasedDiscount;
    private Double discountPercent;
    private Double discountAmount;
    private short quantity;
    private String categoryName;
    private String categoryUrlRouteName;
    private String mainImageSourceRelativePathName;


    public ItemInformation(String name, String sku, double price, boolean isPopular,
                           Boolean isPercentageBasedDiscount, Double discountPercent, Double discountAmount,
                           short quantity, String categoryName, String categoryUrlRouteName,
                           String mainImageRelativePath, String mainImageFileName) {
        this.name = name;
        this.sku = sku;
        this.price = java.text.NumberFormat.getCurrencyInstance().format(price);
        this.isPopular = isPopular;
        this.isPercentageBasedDiscount = isPercentageBasedDiscount;
        this.discountPercent = discountPercent;
        this.discountAmount = discountAmount;
        this.quantity = quantity;
        this.categoryName = categoryName;
        this.categoryUrlRouteName = categoryUrlRouteName;
        this.mainImageSourceRelativePathName = mainImageRelativePath + mainImageFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = java.text.NumberFormat.getCurrencyInstance().format(price);
    }

    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public boolean getIsPercentageBasedDiscount() {
        return isPercentageBasedDiscount;
    }

    public void setIsPercentageBasedDiscount(boolean isPercentageBasedDiscount) {
        this.isPercentageBasedDiscount = isPercentageBasedDiscount;
    }

    public double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUrlRouteName() {
        return categoryUrlRouteName;
    }

    public void setCategoryUrlRouteName(String categoryUrlRouteName) {
        this.categoryUrlRouteName = categoryUrlRouteName;
    }

    public String getMainImageSourceRelativePathName() {
        return mainImageSourceRelativePathName;
    }

    public void setMainImageSourceRelativePathName(String mainImageFileName, String mainImageRelativePath) {
        this.mainImageSourceRelativePathName = mainImageFileName + mainImageRelativePath;
    }
}
