package com.psu.ist311.team5.cornerfoodmarketwebsite.data.domain.entity;

import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.CartItem;
import com.psu.ist311.team5.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@SecondaryTables({
        @SecondaryTable(name = "DISCOUNT", foreignKey = @ForeignKey(name = "DISCOUNT_ID")),
        @SecondaryTable(name = "ITEM_INVENTORY", foreignKey = @ForeignKey(name = "INVENTORY_ID")),
        @SecondaryTable(name = "ITEM_CATEGORY", foreignKey = @ForeignKey(name = "CATEGORY_ID")),
        @SecondaryTable(name = "ITEM_IMAGE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ITEM_ID"))
})
public class AccountItemDetailedInformation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "SKU")
    private String sku;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @Column(name = "IS_POPULAR")
    private boolean isPopular;
    @Column(name = "IS_PERCENTAGE_BASED", table = "DISCOUNT")
    private Boolean isPercentageBasedDiscount;
    @Column(name = "DISCOUNT_PERCENT", table = "DISCOUNT")
    private Double discountPercent;
    @Column(name = "DISCOUNT_AMOUNT", table = "DISCOUNT")
    private Double discountAmount;
    @Column(name = "QUANTITY", table = "ITEM_INVENTORY")
    private short quantity;
    @Column(name = "NAME", table = "ITEM_CATEGORY")
    private String categoryName;
    @Column(name = "URL_ROUTE_NAME", table = "ITEM_CATEGORY")
    private String categoryUrlRouteName;

    @OrderBy(value = "SORT_NUMBER ASC")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private List<ItemImage> images = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private List<CartItem> cartItems = new ArrayList<>();

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(price);
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

    public boolean isPopular() {
        return isPopular;
    }

    public void setPopular(boolean popular) {
        isPopular = popular;
    }

    public Boolean getPercentageBasedDiscount() {
        return isPercentageBasedDiscount;
    }

    public void setPercentageBasedDiscount(Boolean percentageBasedDiscount) {
        isPercentageBasedDiscount = percentageBasedDiscount;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

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

    public List<ItemImage> getImages() {
        return images;
    }

    public void setImages(List<ItemImage> images) {
        this.images = images;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }
}
