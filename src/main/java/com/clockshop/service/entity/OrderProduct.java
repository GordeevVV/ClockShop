package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="order_products")
public class OrderProduct {
    @Id
    private int orderId;
    private int productId;

    public OrderProduct(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderProduct() {

    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
