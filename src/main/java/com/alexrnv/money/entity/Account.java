package com.alexrnv.money.entity;

import java.math.BigDecimal;

/**
 * @author Alex
 */
@Entity
@Table(name = "Customer")
public class Account {
    private String id;
    private BigDecimal amount;

    public Account(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
