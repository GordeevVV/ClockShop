package com.clockshop.service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="mech_types")
public class MechType {
    @Id
    private int mechId;
    private String type;

    public MechType(int mechId, String type) {
        this.mechId = mechId;
        this.type = type;
    }

    public MechType() {

    }

    public int getMechId() {
        return mechId;
    }

    public String getType() {
        return type;
    }
}
