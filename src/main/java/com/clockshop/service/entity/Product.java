package com.clockshop.service.entity;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String release_date;
    private String imageUrl;
    private int stampId;
    private int materialId;
    private int mechId;
    private float price;

    public Product(int productId, String name, String imageUrl, int materialId,int stampId, int mechId, float price) {
        this.productId = productId;
        this.name = name;
        this.imageUrl = imageUrl;
        this.stampId=stampId;
        this.materialId = materialId;
        this.mechId = mechId;
        this.price = price;
    }


    public int getStampId() {
        return stampId;
    }

    public void setStampId(int stampId) {
        this.stampId = stampId;
    }

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

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getMechId() {
        return mechId;
    }

    public void setMechId(int mechId) {
        this.mechId = mechId;
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

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
