package com.clockshop.service.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="stamp")
public class Stamp {
    @Id
    @Column(name = "stamp_id")
    private int stampId;
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "stamp")
//    List<Product> products;
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
