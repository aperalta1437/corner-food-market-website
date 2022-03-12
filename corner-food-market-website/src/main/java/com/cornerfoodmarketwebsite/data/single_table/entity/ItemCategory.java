package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_CATEGORY")
@Getter
@Setter
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
    @Column(name = "IS_LEAF")
    private boolean isLeaf;
}
