package com.tigertext.automation.enums;

public enum QuickPickOptions {

    FIRST("first"),
    SECOND("second"),
    THIRD("third");

    public String typeOfQuickPick;

    QuickPickOptions(String documentType) {
        typeOfQuickPick = documentType;
    }

    public String getDocumentType() {
        return typeOfQuickPick;
    }

    @Override
    public String toString() { return this.typeOfQuickPick;}
}
