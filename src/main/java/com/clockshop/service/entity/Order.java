package com.clockshop.service.entity;

public class Order {
    private int orderId;
    private int customerId;
    private String createdAt;
    private float calcPrice;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public float getCalcPrice() {
        return calcPrice;
    }

    public void setCalcPrice(float calcPrice) {
        this.calcPrice = calcPrice;
    }
}
