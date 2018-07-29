package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class MessageDetailsScreen {
    public MessageDetailsScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String MESSAGEDETAILSTITLEPATH = "Message Details";
    public static final String DYNAMICMESSAGEDETAILSITEMPATH = "//*[contains(@label,'$')]";

    @iOSFindBy(accessibility = MESSAGEDETAILSTITLEPATH)
    private MobileElement messageDetailsTitlePath;

    public MobileElement getMessageDetailsTitlePath() {
        return messageDetailsTitlePath;
    }
}
