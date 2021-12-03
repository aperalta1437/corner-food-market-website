package com.cornerfoodmarketwebsite.data.domain.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.Discount;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@SecondaryTables({
//        @SecondaryTable(name = "DISCOUNT", foreignKey = @ForeignKey(name = "DISCOUNT_ID")),
        @SecondaryTable(name = "ITEM_INVENTORY", foreignKey = @ForeignKey(name = "INVENTORY_ID")),
        @SecondaryTable(name = "ITEM_CATEGORY", foreignKey = @ForeignKey(name = "CATEGORY_ID")),
        @SecondaryTable(name = "CART_ITEM", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ITEM_ID"))
})
public class AccountItemInformation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             // MUST be integer to avoid type mismatch when we perform one-to-one join to get image.
    @Column(name = "NAME")
    private String name;
    @Column(name = "SKU")
    private String sku;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @Column(name = "IS_POPULAR")
    private boolean isPopular;
//    @Column(name = "IS_PERCENTAGE_BASED", table = "DISCOUNT")
//    private Boolean isPercentageBasedDiscount;
//    @Column(name = "DISCOUNT_PERCENT", table = "DISCOUNT")
//    private Double discountPercent;
//    @Column(name = "DISCOUNT_AMOUNT", table = "DISCOUNT")
    private Double discountAmount;
    @Column(name = "QUANTITY", table = "ITEM_INVENTORY")
    private short quantity;
    @Column(name = "NAME", table = "ITEM_CATEGORY")
    private String categoryName;
    @Column(name = "URL_ROUTE_NAME", table = "ITEM_CATEGORY")
    private String categoryUrlRouteName;
    @Column(name = "CUSTOMER_ID", table = "CART_ITEM")
    private Short customerId;
    @Column(name = "IN_CART_QUANTITY", table = "CART_ITEM")
    private Short inCartQuantity;

    @Where(clause = "SORT_NUMBER = 1")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID", referencedColumnName = "ITEM_ID")
    private ItemImage mainImage;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private List<Discount> discounts = new ArrayList<>();

    public short getId() {
        return (short) id;
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

    public String getFormattedUnitPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(price);
    }

    public String getFormattedUnitsTotalPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(price * inCartQuantity);
    }

    public double getUnitsTotalPrice() {
        return price * inCartQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isOnSale() {
        return isOnSale;
    }

    public void setOnSale(boolean onSale) {
        isOnSale = onSale;
    }

    public boolean getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(boolean popular) {
        isPopular = popular;
    }

//    public Boolean getIsPercentageBasedDiscount() {
//        return isPercentageBasedDiscount;
//    }
//
//    public void setIsPercentageBasedDiscount(Boolean percentageBasedDiscount) {
//        isPercentageBasedDiscount = percentageBasedDiscount;
//    }
//
//    public Double getDiscountPercent() {
//        return discountPercent;
//    }
//
//    public void setDiscountPercent(Double discountPercent) {
//        this.discountPercent = discountPercent;
//    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
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

    public Short getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Short customerId) {
        this.customerId = customerId;
    }

    public Short getInCartQuantity() {
        return inCartQuantity;
    }

    public void setInCartQuantity(Short inCartQuantity) {
        this.inCartQuantity = inCartQuantity;
    }

    public ItemImage getMainImage() {
        return mainImage;
    }

    public void setMainImage(ItemImage mainImage) {
        this.mainImage = mainImage;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }
}

