package com.clockshop.service.entity;

public class Stamp {
    private int stampId;
    private String stamp;

    public Stamp(int stampId, String stamp) {
        this.stampId = stampId;
        this.stamp = stamp;
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
