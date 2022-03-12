package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@Setter
@Getter
@NoArgsConstructor
@RequiredArgsConstructor
public class Item {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NonNull
    @Column(name = "NAME")
    private String name;
    @NonNull
    @Column(name = "DESCRIPTION")
    private String description;
    @NonNull
    @Column(name = "UPC")
    private String upc;
    @NonNull
    @Column(name = "CATEGORY_ID")
    private short categoryId;
    @NonNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVENTORY_ID", referencedColumnName = "ID")
    private ItemInventory itemInventory;
    @Column(name = "WHOLESALE_PRICE")
    private Double wholesalePrice;
    @NonNull
    @Column(name = "RETAIL_PRICE")
    private double retailPrice;
    @NonNull
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @NonNull
    @Column(name = "IS_POPULAR")
    private boolean isPopular;
    @Column(name = "POPULARITY_SORT_NUMBER")
    private short popularitySortNumber;
    @Column(name = "HAS_FIXED_POPULARITY")
    private boolean hasFixedPopularity;
    @Column(name = "CREATED_BY_ADMINISTRATOR_ID")
    private short createdByAdministratorId;
    @Column(name = "MODIFIED_BY_ADMINISTRATOR_ID")
    private Short modifiedByAdministratorId;
}