package com.sckill.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderStatus {

    OPEN("Open"),
    CLOSED("Closed");

    private final String description;

    public String getCode() {
        return this.name();
    }

}
