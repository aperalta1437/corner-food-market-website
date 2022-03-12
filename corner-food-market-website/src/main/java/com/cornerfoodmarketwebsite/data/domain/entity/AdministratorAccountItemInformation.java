package com.cornerfoodmarketwebsite.data.domain.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "ITEM")
@SecondaryTables({
//        @SecondaryTable(name = "DISCOUNT", foreignKey = @ForeignKey(name = "DISCOUNT_ID")),
        @SecondaryTable(name = "ITEM_INVENTORY", foreignKey = @ForeignKey(name = "INVENTORY_ID")),
        @SecondaryTable(name = "ITEM_CATEGORY", foreignKey = @ForeignKey(name = "CATEGORY_ID"))
})
@Getter
@Setter
@NoArgsConstructor
public class AdministratorAccountItemInformation {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;             // MUST be integer to avoid type mismatch when we perform one-to-one join to get image.
    @Column(name = "NAME")
    private String name;
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

    @Where(clause = "SORT_NUMBER = 1")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID", referencedColumnName = "ITEM_ID")
    private ItemImage mainImage;
}

