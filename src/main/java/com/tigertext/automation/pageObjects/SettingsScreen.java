package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSBy;
import io.appium.java_client.pagefactory.iOSFindAll;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class SettingsScreen {
    public SettingsScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String LOGOUTBUTTONPATH = "//*[@label='Logout']";
    public static final String LOGOUTYESBUTTONPATH = "//*[@label='Yes']";
    public static final String ORGANIZATIONSBUTTONPATH = "//TTSettingCategoryCell[@label='Organizations']";
    public static final String PINLOCKPATH = "//*[@label='PIN Lock']";
    public static final String DONOTDISTURBDNDPATH = "//*[@label='Do Not Disturb (DND)']";

    @iOSFindBy(xpath = LOGOUTBUTTONPATH)
    private MobileElement logoutButtonPath;
    @iOSFindBy(xpath = LOGOUTYESBUTTONPATH)
    private MobileElement logoutYesButtonPath;
    @iOSFindBy(xpath = ORGANIZATIONSBUTTONPATH)
    private MobileElement organizationButtonPath;
    @iOSFindBy(xpath = PINLOCKPATH)
    private MobileElement pinLockButtonPath;
    @iOSFindBy(xpath = DONOTDISTURBDNDPATH)
    private MobileElement doNotDisturbButtonPath;

    public MobileElement getLogoutButtonPath() {
        return logoutButtonPath;
    }
    public MobileElement getLogoutYesButtonPath() {
        return logoutYesButtonPath;
    }
    public MobileElement getOrganizationButtonPath() {
        return organizationButtonPath;
    }
    public MobileElement getPinLockButtonPath() {
        return pinLockButtonPath;
    }
    public MobileElement getDoNotDisturbButtonPath() {
        return doNotDisturbButtonPath;
    }
}
