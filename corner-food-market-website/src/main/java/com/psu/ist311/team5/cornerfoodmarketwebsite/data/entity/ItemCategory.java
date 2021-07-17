package com.psu.ist311.team5.cornerfoodmarketwebsite.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_CATEGORY")
public class ItemCategory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PARENT_CATEGORY_ID")
    private short parentCategoryId;
    @Column(name = "CATEGORY_LEVEL")
    private short categoryLevel;
    @Column(name = "URL_ROUTE_NAME")
    private String urlRouteName;

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

    public short getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(short parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public short getCategoryLevel() {
        return categoryLevel;
    }

    public void setCategoryLevel(short categoryLevel) {
        this.categoryLevel = categoryLevel;
    }

    public String getUrlRouteName() {
        return urlRouteName;
    }

    public void setUrlRouteName(String urlRouteName) {
        this.urlRouteName = urlRouteName;
    }
}
