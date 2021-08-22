package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
