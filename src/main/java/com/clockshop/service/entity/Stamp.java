package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stamp")
public class Stamp {
    @Id
    private int stampId;
    private String stamp;

    public Stamp(int stampId, String stamp) {
        this.stampId = stampId;
        this.stamp = stamp;
    }

    public Stamp() {

    }

    public int getStampId() {
        return stampId;
    }

    public void setStampId(int stampId) {
        this.stampId = stampId;
    }

    public String getStamp() {
        return stamp;
    }

    public void setStamp(String stamp) {
        this.stamp = stamp;
    }
}
