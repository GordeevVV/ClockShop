package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {
    @Id
    private int orderId;
    private long customerId;
    private LocalDateTime createdAt;
    private float calcPrice;
    private String status;

    public Order(int orderId, float calcPrice, String status) {
        this.orderId = orderId;
        this.calcPrice = calcPrice;
        this.status = status;
    }

    public Order() {

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public float getCalcPrice() {
        return calcPrice;
    }

    public void setCalcPrice(float calcPrice) {
        this.calcPrice = calcPrice;
    }
}
