package com.sckill.sckill.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OrderSituation {

    OPEN("Open"),
    CLOSED("Closed");

    private final String description;

    public String getCode() {
        return this.name();
    }

}
