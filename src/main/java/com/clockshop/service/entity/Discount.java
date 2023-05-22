package com.clockshop.service.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="discounts")
public class Discount {
    @Id
    private int discountId;
    private int productId;
    private int customerId;
    private String name;
    private String description;
    private int percent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
