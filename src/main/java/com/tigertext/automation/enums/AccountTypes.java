package com.tigertext.automation.enums;

public enum AccountTypes {

    ADFS("adfs"),
    SAML("saml"),
    USAANDSINGAPORE("usa and singapore");

    private String accountTypes;

    AccountTypes(String accountTypes) {
        this.accountTypes = accountTypes;
    }

    public String getAccountTypes() {
        return accountTypes;
    }

    @Override
    public String toString() { return this.accountTypes;}
}
