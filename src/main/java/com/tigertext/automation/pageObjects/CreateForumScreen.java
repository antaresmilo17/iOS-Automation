package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateForumScreen {
    public CreateForumScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String ENTERNEWFORUMNAMEPATH = "//*[@value='Enter Forum Name (Required)']";
    public static final String ENTERNEWFORUMDESCRIPTIONPATH = "//*[@value='Describe this Forum (Optional)']";
    public static final String CREATENEWFORUMBUTTONPATH = "Create";
    public static final String ADDMEMBERSTOTEXTFIELDPATH = "To Text Field";

    @iOSFindBy(xpath = ENTERNEWFORUMNAMEPATH)
    private MobileElement enterNewForumNameTextField;
    @iOSFindBy(accessibility = ADDMEMBERSTOTEXTFIELDPATH)
    private MobileElement addMembersToTextField;
    @iOSFindBy(xpath = ENTERNEWFORUMDESCRIPTIONPATH)
    private MobileElement enterNewForumDescriptionTextField;
    @iOSFindBy(accessibility = CREATENEWFORUMBUTTONPATH)
    private MobileElement createNewForumButton;

    public MobileElement getEnterNewForumNameTextField() {
        return enterNewForumNameTextField;
    }
    public MobileElement getEnterNewForumDescriptionTextField() {
        return enterNewForumDescriptionTextField;
    }
    public MobileElement getCreateNewForumButton() {
        return createNewForumButton;
    }
    public MobileElement getAddMembersToTextField() {
        return addMembersToTextField;
    }
}
