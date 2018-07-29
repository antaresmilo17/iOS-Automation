package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class MessageReadByScreen {
    public MessageReadByScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String MESSAGEREADBYPATH = "Message Read By";
    public static final String MESSAGEREADBYUSERSPATH = "//UITableView/UITableViewCellAccessibilityElement";

    @iOSFindBy(accessibility = MESSAGEREADBYPATH)
    private MobileElement messageReadByTitle;
    @iOSFindBy(xpath = MESSAGEREADBYUSERSPATH)
    private List<MobileElement> messageReadByUsers;


    public MobileElement getMessageReadByTitle() {
        return messageReadByTitle;
    }
    public List<MobileElement> getMessageReadByUsers() {
        return messageReadByUsers;
    }
}
