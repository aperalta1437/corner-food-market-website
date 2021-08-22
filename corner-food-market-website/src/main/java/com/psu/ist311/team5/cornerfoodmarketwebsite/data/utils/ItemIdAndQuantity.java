package com.psu.ist311.team5.cornerfoodmarketwebsite.data.utils;

public class ItemIdAndQuantity {
    private short id;
    private short quantity;

    public ItemIdAndQuantity(short id, short quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public short getId() {
        return id;
    }

    public void setId(short itemId) {
        this.id = itemId;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }
}
