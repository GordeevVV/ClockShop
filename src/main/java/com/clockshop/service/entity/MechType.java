package com.clockshop.service.entity;

public class MechType {
    private int mechId;
    private String type;

    public MechType(int mechId, String type) {
        this.mechId = mechId;
        this.type = type;
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
