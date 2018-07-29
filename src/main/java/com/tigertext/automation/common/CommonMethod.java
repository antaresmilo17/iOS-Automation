package com.tigertext.automation.common;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.enums.MessageOptions;
import com.tigertext.automation.enums.QuickPickOptions;
import com.tigertext.automation.pageObjects.ConversationScreen;
import com.tigertext.automation.pageObjects.InboxScreen;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.SettingsScreen;
import com.util.WebDriverController;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.JavascriptExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.logging.Logger;

public class CommonMethod {

    private MobileAPI mobileWebdriver;
    private LoginScreen loginPO = new LoginScreen(WebDriverController.appium_driver);
    private InboxScreen inboxScreen = new InboxScreen(WebDriverController.appium_driver);
    private SettingsScreen settingsScreen = new SettingsScreen(WebDriverController.appium_driver);
    private ConversationScreen conversationScreen = new ConversationScreen(WebDriverController.appium_driver);
    MessageOptions typeOfMessageOptionsEnum;
    QuickPickOptions typeOfQuickPickEnum;
    Logger logger = Logger.getLogger(CommonMethod.class.getName());

    public CommonMethod(MobileAPI mobileWebdriver){
        this.mobileWebdriver = mobileWebdriver;
    }

    public void enterEmail(String username) throws Throwable{
        loginPO.getUserNameTextField().clear();
        loginPO.getUserNameTextField().sendKeys(username);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getNextKeyboardButton(), 80);
            loginPO.getNextKeyboardButton().click();
//        try {
//            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getNextButton(), 60);
//            loginPO.getNextButton().click();
//        }catch (Exception e)

    }

    public void enterPassword(String password) throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getUserPasswordTextField(),60);
        loginPO.getUserPasswordTextField().clear();
        loginPO.getUserPasswordTextField().sendKeys(password);
        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getNextKeyboardButton(), 60);
        loginPO.getNextKeyboardButton().click();
//            try {
//            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getPasswordNextButton(), 30);
//            loginPO.getPasswordNextButton().click();

    }

    public void loginWithUser(String name) throws Throwable{
        String username = TestConfig.Environment.getUserEmail(name);
        String password = TestConfig.Environment.getUserPassword(name);
        loginPO.getGetStartedButton().click();
        enterEmail(username);
        enterPassword(password);
    }

    public void loginWithSingapore(String name, String option) throws Throwable{
        String username = TestConfig.Environment.getUserEmail(name);
        String password = TestConfig.Environment.getUserPassword(name);
        String env = option.trim();
        enterEmail(username);
        if("usa".equalsIgnoreCase(env)){
            mobileWebdriver.waitForElementAndClick(loginPO.getUsaButton());
        }else{
            mobileWebdriver.waitForElementAndClick(loginPO.getIhisButton());
        }
        enterPassword(password);
    }

    public void allowNotifications() throws Throwable{
        boolean contacts = false;
        if(mobileWebdriver.elementExist(loginPO.getActivateButton())){
            loginPO.getActivateButton().click();
            if(mobileWebdriver.elementExist(loginPO.getContactsAlertPopup())){
                mobileWebdriver.waitForElementAndClick(loginPO.getAllowAlertButton());
                contacts = true;
            }
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getiMessageSendButton(),140);
            loginPO.getiMessageSendButton().click();

            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton(), 140);
            loginPO.getAllowAlertButton().click();
//            if(!mobileWebdriver.elementExist(loginPO.getAllowAlertButton(), 140)){
//                mobileWebdriver.waitForElementAndClick(loginPO.getAlertSkipButton());
//            }
        }
        if(!contacts) {
            if (mobileWebdriver.elementExist(loginPO.getContactsAlertPopup())) {
                mobileWebdriver.alertAccept();
            }
        }

        if(!contacts){
            allowNotificationFromLogin();
        }


        if(mobileWebdriver.elementExist(loginPO.getAllowNotificationsPage())) {
            allowNotificationFromLogin();
        }

        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton(), 140);
        loginPO.getAllowAlertButton().click();

        allowNotificationFromLogin();

        try {

            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton(), 140);
            loginPO.getAllowAlertButton().click();

        }catch(Exception e)
        {
            logger.info("NO ALERTS");
        }

        try {
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton(), 140);
            loginPO.getAllowAlertButton().click();
            //mobileWebdriver.alertAccept();
        }
        catch (Exception e)
        {
            logger.info("NO ALERTS");
        }

        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGotItButton(),60);
        loginPO.getGotItButton().click();

        if(mobileWebdriver.elementExist(loginPO.getTurnItOn())){
            loginPO.getTurnItOn().click();
        }

    }

    public void allowNotificationFromLogin() throws Throwable{
        try {
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton());
            loginPO.getAllowAlertButton().click();
        }
        catch (Exception e){
                try {
                    mobileWebdriver.alertAccept();
                } catch (Exception e1) {
                    logger.info("NO ALET FROM ALLOW NOTIF FROM LOGIN");
                }
            }
    }

    public void activatePhone() throws Throwable{
        loginPO.getActivateButton().click();
        loginPO.getiMessageSendButton().click();
    }

    public void enterSAML(String username, String password) throws Throwable{
        mobileWebdriver.elementExist(loginPO.getSamlWebView(), 20);
        loginPO.getSamlEmail().click();
        loginPO.getSamlEmail().sendKeys(username);
        loginPO.getSamlPassword().click();
        loginPO.getSamlPassword().sendKeys(password);
        loginPO.getSamlLogin().click();
    }

    public String randomMessage(int chars) throws Throwable{
        String result = "";
        for(int i = 0; i < chars; i++){
            int rnd = (int) (Math.random() * 52); // or use Random or whatever
            char base = (rnd < 26) ? 'A' : 'a';
            result += (char) (base + rnd % 26);
        }
        return result;
    }

    public void navigateToConversation(String name) throws Throwable{
        mobileWebdriver.waitForElementAndClick(loginPO.getInboxTab(),60);
        //Swipe to get search bar
        if(mobileWebdriver.elementExist(conversationScreen.getSendMessageEmptyStateButton(), 1)){
            inboxScreen.getComposeButton().click();
            inboxScreen.getEnterNameTextField().sendKeys(name);
            mobileWebdriver.returnDynamicElement(inboxScreen.getNewMessageUser(), "$", name).click();
        }else {
            if(!mobileWebdriver.elementExist(inboxScreen.getSearchBar())) {
                TouchAction ta = new TouchAction(WebDriverController.appium_driver);
                int width = WebDriverController.appium_driver.manage().window().getSize().getWidth();
                int height = WebDriverController.appium_driver.manage().window().getSize().getHeight();
                System.out.println("\n\n\nWidth: " + width + "\nHeight: " + height + "\n\n\n");
                ta.press(width / 2, (height / 2) - 250).moveTo(width / 2, (height / 2) + 250).release();
                WebDriverController.appium_driver.performTouchAction(ta);
            }
            mobileWebdriver.waitForPageObjectToBeClickable(inboxScreen.getSearchBar(),140);
            inboxScreen.getSearchBar().sendKeys(name);
            printPageSource();
            inboxScreen.getFirstConversation().click();
        }
    }

    public void navigateToConversationWithOutSearch(String name) throws  Throwable
    {
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(inboxScreen.INBOXCONVERSATIONPATH,"$",name),60);
        mobileWebdriver.clickDynamicElement(inboxScreen.INBOXCONVERSATIONPATH,"$",name);

    }

    public void sendRandomMessage(String message) throws Throwable{
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getMessageTextField(),60);
        conversationScreen.getMessageTextField().click();
        conversationScreen.getMessageTextField().sendKeys(message);
        conversationScreen.getSendButton().click();
    }

    public void sendMessageToUserAPI(String customerToken, String orgToken, String message, String priority, String user){
        // priority 0=off 1=on
        String[] command = {
                "curl",
                TestConfig.Environment.getDevApi() + "message",
                "-H",
                "content-type: application/json",
                "-u",
                TestConfig.Environment.getUserApiKey(user) + ":" + TestConfig.Environment.getUserSecret(user),
                "--data-binary",
                "{\"recipient\":\"" + customerToken + "\",\"body\":\"" + message + "\",\"sender_organization\":\""+ orgToken +"\",\"recipient_organization\":\""+ orgToken +"\",\"priority\":"+ priority +",\"ttl\":43200}"};

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process p;
        try {
            p = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            System.out.print("results:"+result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCurrentTime(){
        long millis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        Date resultdate = new Date(millis);
        return sdf.format(resultdate);
    }

    public MobileElement lastElementXpath (String locator) throws Throwable{
        List<MobileElement> we = conversationScreen.getPhotos();
        System.out.println(WebDriverController.appium_driver.getPageSource());
        return we.get(we.size()-1);
    }

    public void takePhoto (boolean selfie) throws Throwable{
        conversationScreen.getCameraAttachmentButton().click();
        mobileWebdriver.alertAccept();
        if(selfie){
            conversationScreen.getCameraViewBackFacing().click();
        }
        conversationScreen.getCameraTakePhotoButton().click();
        mobileWebdriver.waitForElementAndClick(conversationScreen.getCameraUsePhoto(), 60);
    }

    public void takeVideo (boolean selfie, int seconds) throws Throwable{
        conversationScreen.getCameraAttachmentButton().click();
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)){
            mobileWebdriver.alertAccept();
        }
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getCameraModeVideo2(),60);
        if(mobileWebdriver.elementExist(mobileWebdriver.returnDynamicElement(conversationScreen.getCameraModeVideo(), "$", "photo"),130)){
            MobileElement swipeElement = conversationScreen.getCameraMode();
            System.out.println(swipeElement.getRect().getWidth()+" "+swipeElement.getRect().getHeight());
            TouchAction ts = new TouchAction(WebDriverController.appium_driver);
            ts.tap(swipeElement, swipeElement.getRect().getWidth()/3, swipeElement.getRect().getHeight()/2);
            WebDriverController.appium_driver.performTouchAction(ts);
        }
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)){
            mobileWebdriver.alertAccept();
        }
        if(selfie){
            conversationScreen.getCameraViewBackFacing().click();
        }
        conversationScreen.getCameraRecordVideoButton().click();
        Thread.sleep(seconds*1000);
        conversationScreen.getCameraStopRecordingVideoButton().click();
        conversationScreen.getCameraUseVideo().click();
        conversationScreen.getSendButton().click();
    }

    public void recordVoice(int seconds) throws Throwable{
        conversationScreen.getVoiceAttachmentButton().click();
        mobileWebdriver.alertAccept();
        conversationScreen.getVoiceRecordStartStop().click();
        Thread.sleep(seconds*1000);
        conversationScreen.getVoiceRecordStop().click();
        conversationScreen.getSendOn().click();
    }

    public void sendLocation() throws Throwable{
        conversationScreen.getLocationAttachmentButton().click();
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)){
            mobileWebdriver.alertAccept();
        }
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)){
            mobileWebdriver.alertAccept();
        }
        mobileWebdriver.elementExist(conversationScreen.getLocationAttachmentButton());
    }

    public void printPageSource(){
        System.out.println("\n\n\n"+WebDriverController.appium_driver.getPageSource()+"\n\n\n");
    }

    public void logOut()throws Throwable{
        navigateToTheSettingsPage();
        selectSettingsOption("log out");
    }

    public void selectSentMessageOptions(String messageOption)throws Throwable{
        typeOfMessageOptionsEnum = MessageOptions.valueOf(messageOption.replace(" ","").toUpperCase());
        switch (typeOfMessageOptionsEnum) {
            case DETAILS:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getMessageOptionShowMeMoreItemsButton(),30);
                conversationScreen.getMessageOptionShowMeMoreItemsButton().click();
                mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionDetailsButton(),30);
                break;
            case FORWARD:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getMessageOptionShowMeMoreItemsButton(),30);
                conversationScreen.getMessageOptionShowMeMoreItemsButton().click();
                mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionForwardButton(),30);
                break;
            case RESEND:
                mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionResendButton());
                break;
            case RECALL:
                mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionRecallButton());
                break;
            case RESENDASPRIORITY:
                mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionResendAsPriorityButton());
                break;
            default:
                break;
        }
    }

    public void selectQuickPick(String quickPickOption)throws Throwable{
        typeOfQuickPickEnum = QuickPickOptions.valueOf(quickPickOption.toUpperCase());

        switch(typeOfQuickPickEnum){
            case FIRST:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(conversationScreen.getFirstQuickReplyOption()));
                mobileWebdriver.waitForElementAndClick(conversationScreen.getFirstQuickReplyOption());
                break;
            case SECOND:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(conversationScreen.getSecondQuickReplyOption()));
                mobileWebdriver.waitForElementAndClick(conversationScreen.getSecondQuickReplyOption());
                break;
            case THIRD:
                mobileWebdriver.data.addDictionary("message",mobileWebdriver.returnText(conversationScreen.getThirdQuickReplyOption()));
                mobileWebdriver.waitForElementAndClick(conversationScreen.getThirdQuickReplyOption());
                break;
            default:
                break;
        }
    }

    public void selectSettingsOption(String settingsOption) throws Throwable
    {
        switch (settingsOption.toLowerCase()) {
            case "log out":
                mobileWebdriver.iOSExecutorSwipeBasedOnDirection(settingsScreen.getPinLockButtonPath(), "up");
                mobileWebdriver.waitForPageObjectToBeClickable(settingsScreen.getLogoutButtonPath(),60);
                settingsScreen.getLogoutButtonPath().click();
                mobileWebdriver.waitForPageObjectToBeClickable(settingsScreen.getLogoutYesButtonPath(),60);
                settingsScreen.getLogoutYesButtonPath().click();
                break;
            default:
                break;

        }
    }

    public void navigateToTheSettingsPage() throws Throwable
    {
        mobileWebdriver.waitForPageObjectToBeClickable(inboxScreen.getSettingsButton(), 60);
        inboxScreen.getSettingsButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(settingsScreen.getPinLockButtonPath(), 60);

    }

}
