package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DISCOUNT")
@Getter
@Setter
public class Discount {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ITEM_ID")
    private short itemId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DISCOUNT_TYPE_ID")
    private DiscountType discountType;

    @Column(name = "IS_QUANTITY_BASED")
    private boolean isQuantityBased;
    @Column(name = "IS_PERCENTAGE_BASED")
    private boolean isPercentageBased;
    @Column(name = "IS_AMOUNT_BASED")
    private boolean isAmountBased;
    @Column(name = "DISCOUNT_PERCENT")
    private Double discountPercent;
    @Column(name = "DISCOUNT_AMOUNT")
    private Double discountAmount;
    @Column(name = "IS_ACTIVE")
    private boolean isActive;
    @Column(name = "MINIMUM_CUSTOMER_EXPENDITURE")
    private Double minimumCustomerExpenditure;
    @Column(name = "MAXIMUM_EXPENDITURE_TIMEFRAME")
    private Long maximumExpenditureTimeframe;
    @Column(name = "EFFECTIVE_DATETIME")
    private Date effectiveDatetime;
    @Column(name = "EXPIRATION_DATETIME")
    private Date expirationDatetime;
    @Column(name = "IS_REPEATED")
    private boolean isRepeated;
    @Column(name = "REPEATED_AFTER_TIMEFRAME")
    private Long repeatedAfterTimeframe;
    @Column(name = "TOTAL_REPETITIONS")
    private Short totalRepetitions;
    @Column(name = "IS_MODIFIED")
    private boolean isModified;
    @Column(name = "DISCOUNT_HISTORY_ID")
    private long discountHistoryId;
    @Column(name = "CREATED_BY_ADMINISTRATOR_ID")
    private short createdByAdministratorId;
    @Column(name = "MODIFIED_BY_ADMINISTRATOR_ID")
    private Short modifiedByAdministratorId;
    @Column(name = "CREATED_AT")
    private Date createdAt;
    @Column(name = "MODIFIED_AT")
    private Date modifiedAt;
}
