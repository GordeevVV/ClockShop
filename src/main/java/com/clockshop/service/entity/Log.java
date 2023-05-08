package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name="logs")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String productUrl;

    private String productName;

    private LocalDateTime buyTime;

    private String material;

    private String mechType;

    private String stamp;

    public Log(String productUrl, String productName, LocalDateTime buyTime,
               String material, String mechType, String stamp) {
        this.productUrl = productUrl;
        this.productName = productName;
        this.buyTime = buyTime;
        this.material = material;
        this.mechType = mechType;
        this.stamp = stamp;
    }

    public Log() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public LocalDateTime getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(LocalDateTime buyTime) {
        this.buyTime = buyTime;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getMechType() {
        return mechType;
    }

    public void setMechType(String mechType) {
        this.mechType = mechType;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
