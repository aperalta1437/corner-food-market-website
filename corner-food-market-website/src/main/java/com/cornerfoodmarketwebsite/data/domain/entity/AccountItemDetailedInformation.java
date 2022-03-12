package com.cornerfoodmarketwebsite.data.domain.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
@Getter
@Setter
@NoArgsConstructor
public class AccountItemDetailedInformation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "UPC")
    private String upc;
    @Column(name = "RETAIL_PRICE")
    private double retailPrice;
    @Column(name = "WHOLESALE_PRICE")
    private Double wholesalePrice;
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @Column(name = "IS_POPULAR")
    private boolean isPopular;
//    @Column(name = "IS_PERCENTAGE_BASED", table = "DISCOUNT")
//    private Boolean isPercentageBasedDiscount;
//    @Column(name = "DISCOUNT_PERCENT", table = "DISCOUNT")
//    private Double discountPercent;
//    @Column(name = "DISCOUNT_AMOUNT", table = "DISCOUNT")
//    private Double discountAmount;
    @Column(name = "QUANTITY", table = "ITEM_INVENTORY")
    private short quantity;
    @Column(name = "NAME", table = "ITEM_CATEGORY")
    private String categoryName;
    @Column(name = "URL_ROUTE_NAME", table = "ITEM_CATEGORY")
    private String categoryUrlRouteName;
    @Column(name = "IN_CART_QUANTITY", table = "CART_ITEM")
    private Short inCartQuantity;

    @OrderBy(value = "SORT_NUMBER ASC")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private List<ItemImage> images = new ArrayList<>();

    public String getFormattedUnitRetailPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(retailPrice);
    }

    public String getFormattedUnitsTotalRetailPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(retailPrice * this.inCartQuantity);
    }
}
