package com.cornerfoodmarketwebsite.data.domain.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.Discount;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@Getter
@Setter
@NoArgsConstructor
public class AccountItemInformation {
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

    public String getFormattedUnitRetailPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(retailPrice);
    }

    public String getFormattedUnitsTotalRetailPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(this.getUnitsTotalRetailPrice());
    }

    public double getUnitsTotalRetailPrice() {
        return retailPrice * inCartQuantity;
    }
}

