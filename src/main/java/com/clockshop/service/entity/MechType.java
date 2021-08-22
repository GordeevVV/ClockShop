package com.clockshop.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public void setMechId(int mechId) {
        this.mechId = mechId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
