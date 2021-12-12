package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
public class Item {
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
    @Column(name = "CATEGORY_ID")
    private short categoryId;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
    private ItemInventory itemInventory;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @Column(name = "IS_POPULAR")
    private boolean isPopular;

    public Item(String name, String description, String sku, short categoryId, ItemInventory itemInventory, double price, boolean isOnSale, boolean isPopular) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.categoryId = categoryId;
        this.itemInventory = itemInventory;
        this.price = price;
        this.isOnSale = isOnSale;
        this.isPopular = isPopular;
    }

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

    public void setSKU(String sku) {
        this.sku = sku;
    }

    public short getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(short categoryId) {
        this.categoryId = categoryId;
    }

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public void setItemInventory(ItemInventory itemInventory) {
        this.itemInventory = itemInventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean getIsOnSale() {
        return isOnSale;
    }

    public void setIsOnSale(boolean isOnSale) {
        this.isOnSale = isOnSale;
    }

    public boolean getIsPopular() {
        return isPopular;
    }

    public void setIsPopular(boolean isPopular) {
        this.isPopular = isPopular;
    }
}