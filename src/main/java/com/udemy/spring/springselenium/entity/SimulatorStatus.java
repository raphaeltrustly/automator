package com.udemy.spring.springselenium.entity;

public enum SimulatorStatus {
    SENT("Sent"),
    RECEIVED("Received"),
    REJECTED("Rejected"),
    RETURNED("Returned"),
    NOC("NOC");

    private final String status;

    SimulatorStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
