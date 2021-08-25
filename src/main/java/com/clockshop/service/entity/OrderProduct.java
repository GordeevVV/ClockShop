package com.clockshop.service.entity;

import javax.persistence.*;

@Entity
@Table(name="order_products")
public class OrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderproductId;
    private int orderId;
    private int productId;

    public int getOrderproductId() {
        return orderproductId;
    }

    public void setOrderproductId(int orderproductId) {
        this.orderproductId = orderproductId;
    }

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
