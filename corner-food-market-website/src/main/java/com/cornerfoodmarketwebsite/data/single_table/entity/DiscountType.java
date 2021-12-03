package com.cornerfoodmarketwebsite.data.single_table.entity;

import com.cornerfoodmarketwebsite.data.single_table.entity.utils.DiscountTypeNameEnum;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "DISCOUNT_TYPE")
public class DiscountType {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @Column(name = "NAME")
    private DiscountTypeNameEnum name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "PUBLIC_DESCRIPTION_TEMPLATE")
    private String publicDescriptionTemplate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "discountType")
    private List<Discount> discounts;

    public short getId() {
        return id;
    }

    public DiscountTypeNameEnum getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPublicDescriptionTemplate() {
        return publicDescriptionTemplate;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
