package com.clockshop.service.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
public class Product {
    @Id
    private int productId;
    private String name;
    private String description;
    private LocalDateTime release_date;
    private String imageUrl;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "stamp_id",referencedColumnName = "stamp_id")
//    private Stamp stamp_id;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "material_id")
//    private Material material;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "mech_id")
//    private MechType mechType;
    private int stampId;
    private int materialId;
    private int mechId;
    private float price;

    public Product() {

    }

    public int getMechId() {
        return mechId;
    }

    public void setMechId(int mechId) {
        this.mechId = mechId;
    }

    public int getStampId() {
        return stampId;
    }

    public void setStampId(int stampId) {
        this.stampId = stampId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }


//    public Stamp getStamp_id() {
//        return stamp_id;
//    }
//
//    public void setStamp_id(Stamp stamp) {
//        this.stamp_id = stamp;
//    }
//
//
//    public Material getMaterial() {
//        return material;
//    }
//
//    public void setMaterial(Material material) {
//        this.material = material;
//    }
//
//
//    public MechType getMechType() {
//        return mechType;
//    }
//
//    public void setMechType(MechType mechType) {
//        this.mechType = mechType;
//    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getRelease_date() {
        return release_date;
    }

    public void setRelease_date(LocalDateTime release_date) {
        this.release_date = release_date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
