package com.alexrnv.money.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Alex
 */
@Entity
@Table(name = "Accounts")
public class Account implements Serializable {

    @Id
    private String id;

    private BigDecimal amount;

    public Account(){}

    public Account(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", amount=" + amount +
                '}';
    }
}
