package com.tigertext.automation.common;

import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class StaticData {
    public static HashMap<String, String> errorMessageStaticData = new HashMap<String, String>(){
        {
            put("invalid user", "Create Password");
            put("invalid format", "Company email addresses are required. Please try again.");
            put("invalid password", "Credentials didn’t match.");
        }
    };

    public static final String messageIdRegex = "ID:\\s[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
    public static final String senderTokenRegex = "Sender:\\s[0-9a-z]{8}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{4}-[0-9a-z]{12}";
    public static final String messageDateRegex = "Server Date:\\s[0-9]{1}\\D[0-9]{2}\\D[0-9]{2}";
    public static final String linkMessage = "https://www.google.com";
    public static final String everyoneMessageDefaultText = "[Everyone]: ";
}