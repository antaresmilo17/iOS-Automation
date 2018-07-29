package com.tigertext.automation.enums;

public enum InvalidDataTypes {

    INVALIDUSER("invalid user"),
    INVALIDFORMAT("invalid format"),
    INVALIDPASSWORD("invalid password"),
    USAANDSINGAPORE("usa and singapore"),
    SAML("saml"),
    EMPTY("");

    private String invalidDataType;

    InvalidDataTypes(String invalidDataType) {
        this.invalidDataType = invalidDataType;
    }

    public String getInvalidDataType() {
        return invalidDataType;
    }

    @Override
    public String toString() { return this.invalidDataType;}
}
