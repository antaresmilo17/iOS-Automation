package com.tigertext.automation.enums;

public enum MessageOptions {

    DETAILS("details"),
    FORWARD("forward"),
    RESEND("resend"),
    RECALL("recall"),
    RESENDASPRIORITY("resend as priority");
    public String typeOfMessageOption;

    MessageOptions(String documentType) {
        typeOfMessageOption = documentType;
    }

    public String getDocumentType() {
        return typeOfMessageOption;
    }

    @Override
    public String toString() { return this.typeOfMessageOption;}
}
