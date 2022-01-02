package com.cornerfoodmarketwebsite.data.single_table.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "DISCOUNT")
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

    public int getId() {
        return id;
    }

    public short getItemId() {
        return itemId;
    }

    public void setItemId(short itemId) {
        this.itemId = itemId;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public boolean isQuantityBased() {
        return isQuantityBased;
    }

    public void setQuantityBased(boolean quantityBased) {
        isQuantityBased = quantityBased;
    }

    public boolean isPercentageBased() {
        return isPercentageBased;
    }

    public void setPercentageBased(boolean percentageBased) {
        isPercentageBased = percentageBased;
    }

    public boolean isAmountBased() {
        return isAmountBased;
    }

    public void setAmountBased(boolean amountBased) {
        isAmountBased = amountBased;
    }

    public Double getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(Double discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Double getMinimumCustomerExpenditure() {
        return minimumCustomerExpenditure;
    }

    public void setMinimumCustomerExpenditure(Double minimumCustomerExpenditure) {
        this.minimumCustomerExpenditure = minimumCustomerExpenditure;
    }

    public Long getMaximumExpenditureTimeframe() {
        return maximumExpenditureTimeframe;
    }

    public void setMaximumExpenditureTimeframe(Long maximumExpenditureTimeframe) {
        this.maximumExpenditureTimeframe = maximumExpenditureTimeframe;
    }

    public Date getEffectiveDatetime() {
        return effectiveDatetime;
    }

    public void setEffectiveDatetime(Date effectiveDatetime) {
        this.effectiveDatetime = effectiveDatetime;
    }

    public Date getExpirationDatetime() {
        return expirationDatetime;
    }

    public void setExpirationDatetime(Date expirationDatetime) {
        this.expirationDatetime = expirationDatetime;
    }

    public boolean isRepeated() {
        return isRepeated;
    }

    public void setRepeated(boolean repeated) {
        isRepeated = repeated;
    }

    public Long getRepeatedAfterTimeframe() {
        return repeatedAfterTimeframe;
    }

    public void setRepeatedAfterTimeframe(Long repeatedAfterTimeframe) {
        this.repeatedAfterTimeframe = repeatedAfterTimeframe;
    }

    public Short getTotalRepetitions() {
        return totalRepetitions;
    }

    public void setTotalRepetitions(Short totalRepetitions) {
        this.totalRepetitions = totalRepetitions;
    }

    public boolean isModified() {
        return isModified;
    }

    public void setModified(boolean modified) {
        isModified = modified;
    }

    public long getDiscountHistoryId() {
        return discountHistoryId;
    }

    public void setDiscountHistoryId(long discountHistoryId) {
        this.discountHistoryId = discountHistoryId;
    }

    public short getCreatedByAdministratorId() {
        return createdByAdministratorId;
    }

    public void setCreatedByAdministratorId(short createdByAdministratorId) {
        this.createdByAdministratorId = createdByAdministratorId;
    }

    public Short getModifiedByAdministratorId() {
        return modifiedByAdministratorId;
    }

    public void setModifiedByAdministratorId(Short modifiedByAdministratorId) {
        this.modifiedByAdministratorId = modifiedByAdministratorId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }
}
