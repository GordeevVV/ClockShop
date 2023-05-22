package com.clockshop.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="materials")
public class Material {
    @Id
    private int materialId;
    private String material;

    public Material(int materialId, String material) {
        this.materialId = materialId;
        this.material = material;
    }

    public Material() {

    }

    public int getMaterialId() {
        return materialId;
    }

    public String getMaterial() {
        return material;
    }
}
