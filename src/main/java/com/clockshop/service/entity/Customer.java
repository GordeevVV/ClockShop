package com.clockshop.service.entity;

import javax.persistence.*;

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

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
