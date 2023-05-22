package com.clockshop.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
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
