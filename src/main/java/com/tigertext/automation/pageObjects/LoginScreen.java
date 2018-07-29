package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class LoginScreen {

    public LoginScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String GETSTARTEDBUTTONPATH = "Get Started";
    public static final String GOTITBUTTONPATH = "Got It";
    public static final String USERNAMETEXTFIELDPATH = "Work username or email";
    public static final String USERPASSWORDTEXTFIELDPATH1 = "(//*[contains(@label, 'Enter Password')])[2]";
    public static final String USERPASSWORDTEXTFIELDPATH2 = "Enter Password";
    public static final String CONTACTSALERTOPOPUPPATH = "//*[contains(@label, 'Would Like to Access Your Contacts')]";
    public static final String SECUREYOURPHONEPATH = "Phone is Secured!";
    public static final String NOTIFICATIONALERTPOPUPPATH = "//*[contains(@label, 'Would Like to Send You Notifications')]";
    public static final String NEXTBUTTONPATH = "Next";
    public static final String NEXTKEYBOARDBUTTONPATH = "Next:";
    public static final String ENVIRONMENTLABLEPATH = "//*[contains(@label, 'Environment')]";

    public static final String INBOXTABPATH = "Inbox Tab";
    public static final String GROUPSTABPATH =  "Groups Tab";
    public static final String ALLOWALERTBUTTONPATH1 = "Allow";
    public static final String ALLOWALERTBUTTONPATH2 = "OK";
    public static final String DONEPICKERBUTTONPATH = "Done";
    public static final String ACTIVATEBUTTONPATH = "Activate";
    public static final String VERIFYYOUREMAILBUTTONPATH = "Verify Your Email";
    public static final String CREDENTIALDIDNTMATCHTEXTPATH = "Credentials didn’t match.";
    public static final String IMESSAGESENDBUTTONPATH = "Send";
    public static final String ALERTSKIPBUTTONPATH = "Skip";

    public static final String CREATEPASSWORDPATH = "Create Password";
    public static final String INVALIDFORMATPATH = "Company email addresses are required. Please try again.";
    public static final String INVALIDPASSWORDPATH = "Credentials didn’t match.";
    public static final String FORGOTPASSWORDPATH = "Forgot Password";
    public static final String FORGOTPASSWORDCHECKPATH = "//*[contains(@label, 'Please check ')]";
    public static final String FORGOTPASSWORDCLOSEPATH = "close";
    public static final String ALLOWNOTIFICATIONSPAGEPATH = "Get Notifications";
    public static final String ALLOWCONTACTSPAGEPATH = "Get Contacts";
    public static final String CLICK2CALLONPATH = "Turn it On";
    public static final String USABUTTONPATH = "USA";

    public static final String IHISBUTTONPATH = "Singapore";
    public static final String SAMLWEBVIEWPATH = "//*[@class = 'XCUIElementTypeWebView']";
    public static final String SAMLEMAILPATH = "//*[@value = 'Email/Username']";
    public static final String SAMLPASSWORDPATH = "//*[@value = 'Password']";
    public static final String SAMLLOGINPATH = "Log in";
    public static final String TURNITONPATH = "Turn it On";
    public static final String CANCELBUTTONPATH = "Cancel";
    public final String HELLOTEXT = "//*[@label='Enter your work username or email']";
    public final String HELPBUTTON = "//*[@label='Help']";

    public static final String ACCESSMICROPHONEPATH = "//*[contains(text(), 'Would Like to Access the Microphone')]";


    @iOSFindBy(xpath = ACCESSMICROPHONEPATH)
    private MobileElement accessMicrophonePath;


    @iOSFindBy(accessibility=GETSTARTEDBUTTONPATH)
    private MobileElement getStartedButton;
    @iOSFindBy(accessibility=GOTITBUTTONPATH)
    private MobileElement gotItButton;
    @iOSFindBy(accessibility=USERNAMETEXTFIELDPATH)
    private MobileElement userNameTextField;
    @iOSFindAll({@iOSBy(xpath = USERPASSWORDTEXTFIELDPATH1), @iOSBy(accessibility = USERPASSWORDTEXTFIELDPATH2)})
    private MobileElement userPasswordTextField;
    @iOSFindBy(xpath = CONTACTSALERTOPOPUPPATH)
    private MobileElement contactsAlertPopup;
    @iOSFindBy(xpath = NOTIFICATIONALERTPOPUPPATH)
    private MobileElement notificationAlertPopup;
    @iOSFindBy(accessibility=NEXTBUTTONPATH)
    private MobileElement nextButton;
    @iOSFindBy(accessibility=NEXTBUTTONPATH)
    private MobileElement passwordNextButton;
    @iOSFindBy(accessibility = NEXTKEYBOARDBUTTONPATH)
    private MobileElement nextKeyboardButton;
    @iOSFindBy(xpath = ENVIRONMENTLABLEPATH)
    private MobileElement environmentLabel;
    @iOSFindBy(accessibility = INBOXTABPATH)
    private MobileElement inboxTab;

    @iOSFindBy(accessibility = GROUPSTABPATH)
    private MobileElement groupsTab;
    @iOSFindBy(accessibility=DONEPICKERBUTTONPATH)
    private MobileElement donePickerButton;


    @iOSFindAll({@iOSBy(accessibility = ALLOWALERTBUTTONPATH1), @iOSBy(accessibility = ALLOWALERTBUTTONPATH2)})
    private MobileElement allowAlertButton;

//    @iOSFindBy(accessibility = ALLOWALERTBUTTONPATH1)
//    private MobileElement allowAlertButton;
//    @iOSFindBy(accessibility =  ALLOWALERTBUTTONPATH2)
//    private MobileElement okAlertButton;
    @iOSFindBy(accessibility =  SECUREYOURPHONEPATH)
    private MobileElement securePhoneScreenTitle;

    @iOSFindBy(accessibility=ACTIVATEBUTTONPATH)
    private MobileElement activateButton;
    @iOSFindBy(accessibility = VERIFYYOUREMAILBUTTONPATH)
    private MobileElement verifyYourEmailButton;
    @iOSFindBy(accessibility = CREDENTIALDIDNTMATCHTEXTPATH)
    private MobileElement credentialsDidntMatchText;
    @iOSFindBy(accessibility = IMESSAGESENDBUTTONPATH)
    private MobileElement iMessageSendButton;
    @iOSFindBy(accessibility = ALERTSKIPBUTTONPATH)
    private MobileElement alertSkipButton;
    @iOSFindBy(accessibility = CREATEPASSWORDPATH)
    private MobileElement createPassword;
    @iOSFindBy(accessibility = INVALIDFORMATPATH)
    private MobileElement invalidFormat;

    @iOSFindBy(accessibility = INVALIDPASSWORDPATH)
    private MobileElement invalidPassword;
    @iOSFindBy(accessibility = FORGOTPASSWORDPATH)
    private MobileElement forgotPassword;
    @iOSFindBy(xpath = FORGOTPASSWORDCHECKPATH)
    private MobileElement forgotPasswordCheck;
    @iOSFindBy(accessibility = FORGOTPASSWORDCLOSEPATH)
    private MobileElement forgotPasswordClose;
    @iOSFindBy(accessibility = ALLOWNOTIFICATIONSPAGEPATH)
    private MobileElement allowNotificationsPage;
    @iOSFindBy(accessibility = ALLOWCONTACTSPAGEPATH)
    private MobileElement allowContactsPage;
    @iOSFindBy(accessibility = CLICK2CALLONPATH)
    private MobileElement click2CallOn;
    @iOSFindBy(accessibility = USABUTTONPATH)
    private MobileElement usaButton;
    @iOSFindBy(accessibility = IHISBUTTONPATH)
    private MobileElement ihisButton;
    @iOSFindBy(xpath = SAMLWEBVIEWPATH)
    private MobileElement samlWebView;

    @iOSFindBy(xpath = SAMLEMAILPATH)
    private MobileElement samlEmail;
    @iOSFindBy(xpath = SAMLPASSWORDPATH)
    private MobileElement samlPassword;
    @iOSFindBy(accessibility = SAMLLOGINPATH)
    private MobileElement samlLogin;
    @iOSFindBy(accessibility = TURNITONPATH)
    private MobileElement turnItOn;
    @iOSFindBy(accessibility = CANCELBUTTONPATH)
    private MobileElement cancelButton;

    public MobileElement getSecurePhoneScreenTitle() {
        return securePhoneScreenTitle;
    }

//    public MobileElement getOkAlertButton() {
//        return okAlertButton;
//    }

    public MobileElement getGetStartedButton() {
        return getStartedButton;
    }
    public MobileElement getUserNameTextField() {
        return userNameTextField;
    }
    public MobileElement getUserPasswordTextField() {
        return userPasswordTextField;
    }
    public MobileElement getContactsAlertPopup() {
        return contactsAlertPopup;
    }
    public MobileElement getNotificationAlertPopup() {
        return notificationAlertPopup;
    }
    public MobileElement getNextButton() {
        return nextButton;
    }
    public MobileElement getPasswordNextButton() {
        return passwordNextButton;
    }
    public MobileElement getNextKeyboardButton() {
        return nextKeyboardButton;
    }
    public MobileElement getEnvironmentLabel() {
        return environmentLabel;
    }

    public MobileElement getInboxTab() {
        return inboxTab;
    }
    public MobileElement getGroupsTab() {
        return groupsTab;
    }
    public MobileElement getDonePickerButton() {
        return donePickerButton;
    }
    public MobileElement getAllowAlertButton() {
        return allowAlertButton;
    }
    public MobileElement getActivateButton() {
        return activateButton;
    }
    public MobileElement getVerifyYourEmailButton() {
        return verifyYourEmailButton;
    }
    public MobileElement getCredentialsDidntMatchText() {
        return credentialsDidntMatchText;
    }
    public MobileElement getiMessageSendButton() {
        return iMessageSendButton;
    }
    public MobileElement getAlertSkipButton() {
        return alertSkipButton;
    }
    public MobileElement getCreatePassword() {
        return createPassword;
    }

    public MobileElement getInvalidFormat() {
        return invalidFormat;
    }
    public MobileElement getInvalidPassword() {
        return invalidPassword;
    }
    public MobileElement getForgotPassword() {
        return forgotPassword;
    }
    public MobileElement getForgotPasswordCheck() {
        return forgotPasswordCheck;
    }
    public MobileElement getForgotPasswordClose() {
        return forgotPasswordClose;
    }
    public MobileElement getAllowNotificationsPage() {
        return allowNotificationsPage;
    }
    public MobileElement getAllowContactsPage() {
        return allowContactsPage;
    }
    public MobileElement getClick2CallOn() {
        return click2CallOn;
    }
    public MobileElement getUsaButton() {
        return usaButton;
    }
    public MobileElement getIhisButton() {
        return ihisButton;
    }

    public MobileElement getSamlWebView() {
        return samlWebView;
    }
    public MobileElement getSamlEmail() {
        return samlEmail;
    }
    public MobileElement getSamlPassword() {
        return samlPassword;
    }
    public MobileElement getSamlLogin() {
        return samlLogin;
    }
    public MobileElement getTurnItOn() {
        return turnItOn;
    }
    public MobileElement getCancelButton() {
        return cancelButton;
    }
    public String getHelloText() {
        return HELLOTEXT;
    }
    public String getHelpButton() {
        return HELPBUTTON;
    }

    public MobileElement getGotItButton() {
        return gotItButton;
    }

    public MobileElement getAccessMicrophonePath() {
        return accessMicrophonePath;
    }
}