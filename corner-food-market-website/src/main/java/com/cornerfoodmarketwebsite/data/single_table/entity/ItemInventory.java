package com.cornerfoodmarketwebsite.data.single_table.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ITEM_INVENTORY")
@Getter
@Setter
@RequiredArgsConstructor
public class ItemInventory {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private short id;
    @NonNull
    @Column(name = "QUANTITY")
    private short quantity;
}