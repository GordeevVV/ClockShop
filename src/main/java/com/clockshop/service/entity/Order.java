package com.clockshop.service.entity;

public class Order {
    private int order_id;
    private int customer_id;
    private String created_at;
    private float calc_price;

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public float getCalc_price() {
        return calc_price;
    }

    public void setCalc_price(float calc_price) {
        this.calc_price = calc_price;
    }
}
