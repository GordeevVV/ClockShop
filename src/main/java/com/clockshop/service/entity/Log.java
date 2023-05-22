package com.clockshop.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
