package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

public class InboxScreen {
    public InboxScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String SEARCHBARPATH = "//*[@value='Search']";
    public static final String INBOXCONVERSATIONPATH = "//*[contains(@label,'$')]";
    public static final String FIRSTCONVERSATIONPATH = "First Conversation";
    public static final String FIRSTCONVERSATIONPRIORITYPATH = "//*[contains(@label,'First Conversation Priority')]";
    public static final String ENTERNAMETEXTFIELDPATH = "Enter Name";
    public static final String CLEARTEXTBUTTONPATH = "Clear text";
    public static final String CANCELBUTTONPATH = "Clear text";
    public static final String COMPOSEBUTTONPATH = "Compose Button";
    public static final String NEWMESSAGEUSERPATH = "//*[@label = '$']";
    public static final String SETTINGSBUTTONPATH = "//*[@label = 'Settings Tab']";

    @iOSFindBy(xpath = SEARCHBARPATH)
    private MobileElement searchBar;
    @iOSFindBy(accessibility = FIRSTCONVERSATIONPATH)
    private MobileElement firstConversation;
    @iOSFindBy(accessibility = CLEARTEXTBUTTONPATH)
    private MobileElement clearTextButton;
    @iOSFindBy(accessibility = CANCELBUTTONPATH)
    private MobileElement cancelButton;
    @iOSFindBy(accessibility = ENTERNAMETEXTFIELDPATH)
    private MobileElement enterNameTextField;
    @iOSFindBy(accessibility = COMPOSEBUTTONPATH)
    private MobileElement composeButton;
    @iOSFindBy( xpath = SETTINGSBUTTONPATH)
    private MobileElement settingsButton;

    public MobileElement getSearchBar() {
        return searchBar;
    }
    public MobileElement getFirstConversation() {
        return firstConversation;
    }
    public MobileElement getEnterNameTextField() {
        return enterNameTextField;
    }
    public MobileElement getComposeButton() {
        return composeButton;
    }
    public String getNewMessageUser() {
        return NEWMESSAGEUSERPATH;
    }
    public MobileElement getSettingsButton() {
        return settingsButton;
    }
    public MobileElement getClearTextButton() {
        return clearTextButton;
    }
    public MobileElement getCancelButton() {
        return cancelButton;
    }
}
