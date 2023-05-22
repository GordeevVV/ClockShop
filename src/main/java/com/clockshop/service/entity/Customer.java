package com.clockshop.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer {
    @Id
    private long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phone;

    public Customer(long customerId, String first_name, String last_name, String phone) {
        this.customerId = customerId;
        this.firstName = first_name;
        this.lastName = last_name;
        this.phone = phone;
    }

    public Customer() {

    }

    public long getCustomerId() {
        return  customerId;
    }
}
