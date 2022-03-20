package com.cornerfoodmarketwebsite.data.domain.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.Discount;
import com.cornerfoodmarketwebsite.data.single_table.entity.ItemImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ITEM")
@SecondaryTables({
        @SecondaryTable(name = "ITEM_INVENTORY", foreignKey = @ForeignKey(name = "INVENTORY_ID")),
        @SecondaryTable(name = "ITEM_CATEGORY", foreignKey = @ForeignKey(name = "CATEGORY_ID")),
        @SecondaryTable(name = "ITEM_IMAGE", pkJoinColumns = @PrimaryKeyJoinColumn(name = "ITEM_ID"))
})
@Getter
@Setter
@NoArgsConstructor
public class ItemInformation implements Serializable {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "NAME")
    private String name;
    @Column(name = "UPC")
    private String upc;
    @Column(name = "WHOLESALE_PRICE")
    private Double wholesalePrice;
    @Column(name = "RETAIL_PRICE")
    private double retailPrice;
    @Column(name = "IS_ON_SALE")
    private boolean isOnSale;
    @Column(name = "IS_POPULAR")
    private boolean isPopular;
    @Column(name = "QUANTITY", table = "ITEM_INVENTORY")
    private short quantity;                                         // We need quantity to avoid toggling IS_ON_SALE if there is none.
    @Column(name = "NAME", table = "ITEM_CATEGORY")
    private String categoryName;
    @Column(name = "URL_ROUTE_NAME", table = "ITEM_CATEGORY")
    private String categoryUrlRouteName;

    @Where(clause = "SORT_NUMBER = 1")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    private List<ItemImage> images = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Discount> discounts = new ArrayList<>();

    public String getFormattedRetailPrice() {
        return java.text.NumberFormat.getCurrencyInstance().format(retailPrice);
    }
}
