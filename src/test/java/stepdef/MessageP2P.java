package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethod;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.enums.AttachmentTypes;
import com.tigertext.automation.pageObjects.ConversationScreen;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;

import java.util.List;
import java.util.logging.Logger;

public class MessageP2P {
    MobileAPI mobileWebdriver = new MobileAPI();
    CommonMethod common  = new CommonMethod(mobileWebdriver);
    LoginScreen loginPO  = new LoginScreen(WebDriverController.appium_driver);
    ConversationScreen conversationScreen = new ConversationScreen(WebDriverController.appium_driver);
    AttachmentTypes attachmentTypes;
    Login login = new Login();
    Logger logger = Logger.getLogger(MessageP2P.class.getName());


    @Given("I am logged in as \"(.*)\"$")
    public void loginAs(String user) throws Throwable {
        mobileWebdriver.data.addDictionary("loginUser", user);
        mobileWebdriver.data.addDictionary("email", TestConfig.Environment.getUserEmail(user));
        mobileWebdriver.data.addDictionary("password", TestConfig.Environment.getUserPassword(user));
        //common.loginWithUser(user);
        try {
            login.iEnterTheCredentialsForDeviceType("true", user, "non sim");
            login.acceptTheNotificationAlerts("true");
        }catch (Exception e)
        {
            logger.info("ALREADY LOGGED IN");
        }
    }

    @When("^I send a (.*) (.*) message to \"([^\"]*)\"$")
    public void iSendAPPMessageTo(String messageType, String messageStatus, String recipientUser) throws Throwable {
        mobileWebdriver.data.addDictionary("recipientUser", recipientUser);
        mobileWebdriver.data.addDictionary("messageType", messageType.trim().toLowerCase());
        mobileWebdriver.data.addDictionary("messageStatus", messageStatus.trim().toLowerCase());
        String status = mobileWebdriver.data.getDictionary("messageStatus");
        String message = "";
        String user = mobileWebdriver.data.getDictionary("recipientUser");
        String typeOfMessage = mobileWebdriver.data.getDictionary("messageType");

        switch (typeOfMessage) {
            case "random":
                message = common.randomMessage(16);
                mobileWebdriver.data.addDictionary("message", message);
                sendP2Pmessage(status, message);
                break;
            case "link":
                message = StaticData.linkMessage;
                mobileWebdriver.data.addDictionary("message", message);
                sendP2Pmessage(status, message);
                break;
            case "emoji":
                message = ConversationScreen.LOLEMOJIPATH;
                mobileWebdriver.data.addDictionary("message", message);
                sendLolEmoji();
                break;
            default:
                break;
        }
    }

    private void sendRedBalloonEmoji() {
        conversationScreen.getMessageTextField().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getEmojiKeyboardButton(),60);
        conversationScreen.getEmojiKeyboardButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getRedBalloonEmojiButton(),60);
        conversationScreen.getRedBalloonEmojiButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),60);
        conversationScreen.getSendButton().click();
    }
    private void sendLolEmoji()throws Throwable {
        conversationScreen.getMessageTextField().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getEmojiKeyboardButton(),60);
        conversationScreen.getEmojiKeyboardButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLolEmojiButton(),140);
        conversationScreen.getLolEmojiButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),60);
        conversationScreen.getSendButton().click();
    }

    private void sendP2Pmessage(String status, String message) throws Throwable {
        if (status.contains("priority")) {
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getPmButton(),60);
            conversationScreen.getPmButton().click();
            if (mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)) {
                mobileWebdriver.alertAccept();
            }
        }
        if(status.contains("attachment")){
            common.takePhoto(false);
            conversationScreen.getSendButton().click();
        }else {
            common.sendRandomMessage(message);
            mobileWebdriver.data.addDictionary("time", common.getCurrentTime());
        }
    }

    @Then("^I should see the latest message sent/delivered status$")
    public void iShouldSeeTheSentDeliveredStatusTo() throws Throwable {
        String messageType = mobileWebdriver.data.getDictionary("messageType");
        String messageStatus = mobileWebdriver.data.getDictionary("messageStatus");
        String message = mobileWebdriver.data.getDictionary("message");
        switch(messageType){
            case "random":
                verifyMessageSent(messageStatus, message);
                break;
            case "link":
                verifyMessageSent(messageStatus, message);
                break;
            case "emoji":
                verifyMessageSent(messageStatus, message);
                break;
            case "quickReply":
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageStatus(),35);
                common.printPageSource();
                System.out.println("Message: "+ message);
                System.out.println("List: "+conversationScreen.getDynamicQuickPickMessageValue());
//                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getDynamicQuickPickMessageValue());
//                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
                break;
            case "random everyone":
                verifyMessageSent(messageStatus,message);
                break;
            default:
                break;
        }
//        WebDriverController.getAppiumDriver().navigate().back();
//        mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getCancelButton(),60);
//        loginPO.getCancelButton().click();
//        common.logOut();

    }

    private void verifyMessageSent(String messageStatus, String message) throws Throwable {
        if(messageStatus.contains("priority")){
            if(messageStatus.contains("attachment")){
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageHasImagePriority(),35);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageHasImagePriority());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessagePriorityHasImageStatus());
            }else {
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessagePriority(),35);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessagePriority());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessagePriorityStatus());
                if(messageStatus.toLowerCase().contains("everyone"))
                {
                    String everyoneMessage = StaticData.everyoneMessageDefaultText + message;
                    mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(ConversationScreen.DYNAMICMESSAGEVALUE,"$",everyoneMessage));

                }
            }
        }else {
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageStatus(),35);
            mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(conversationScreen.getDynamicLatestmessage(), "$", message));
            mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
        }








    }

    @When("^I send a (.*) attachment to \"([^\"]*)\"$")
    public void iSendATypeAttachmentTo(String messageType, String user) throws Throwable {
        String recipient = "";
        if(!"everyone".equals(user.toLowerCase()) && !"auto qa forum".equals(user.toLowerCase())) {
            recipient = TestConfig.Environment.getUserDisplayName(recipient);
        }
        else{
            if("auto qa forum".equals(user.toLowerCase())) {
                recipient = "Auto QA Forum";
            }
            else
            {
                recipient = "Everyone";
            }
        }
        mobileWebdriver.data.addDictionary("recipientUser",recipient);
        mobileWebdriver.data.addDictionary("messageType", messageType.trim().toLowerCase());
        if(!"everyone".equals(user.toLowerCase())) {
            common.navigateToConversation(user);
        }
        attachmentTypes = AttachmentTypes.valueOf(messageType.replace(" ","").toUpperCase());
        switch(attachmentTypes){
            case IMAGELIBRARY:
                iAttachAnImageFromLibrary();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),30);
                conversationScreen.getSendButton().click();
                break;
            case IMAGECAMERA:
                common.takePhoto(false);
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),30);
                conversationScreen.getSendButton().click();
                break;
            case VIDEOLIBRARY:
                iAttachAVideoFromLibrary();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),30);
                conversationScreen.getSendButton().click();
                break;
            case VIDEOCAMERA:
                common.takeVideo(false, 3);
                break;
            case VOICE:
                common.recordVoice(3);
                break;
            case LOCATION:
                common.sendLocation();
                break;
            case PDF:
                iAttachAPdfFile();
                break;
            case DOC:
                break;
            case PPT:
                break;
            case XCEL:
                break;
            default:
                break;
        }
        //conversationScreen.getSendButton().click();
    }

    private void iAttachAVideoFromLibrary() {
        conversationScreen.getPhotoAttachmentButton().click();
        conversationScreen.getCameraRoll().click();
        mobileWebdriver.returnDynamicElement(conversationScreen.getCameraRollMediaType(), "$", "Video").click();
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)) mobileWebdriver.alertAccept();
    }

    private void iAttachAPdfFile() {
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getFileAttachmentButton(),60);
        conversationScreen.getFileAttachmentButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getBrowseSelectedTab(),60);
        conversationScreen.getBrowseSelectedTab().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getPdfDocument(),30);
        conversationScreen.getPdfDocument().click();
    }

    private void iAttachAnImageFromLibrary() {
        conversationScreen.getPhotoAttachmentButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getAllPhotosPath(),60);
        conversationScreen.getAllPhotosPath().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getAllPhotosPath(),60);
        List<MobileElement> test = conversationScreen.getCameraRollImageTrait();
        test.get(0).click();
        // mobileWebdriver.returnDynamicElement(conversationScreen.getCameraRollMediaType(), "$", "Photo,").click();
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)) mobileWebdriver.alertAccept();
    }

    @Then("^I should see the latest attachment sent/delivered status$")
    public void iShouldSeeTheLatestAttachmentSentDeliveredStatus() throws Throwable {
        String messageType = mobileWebdriver.data.getDictionary("messageType");
        attachmentTypes = AttachmentTypes.valueOf(messageType.replace(" ","").toUpperCase());
        switch(attachmentTypes){
            case IMAGELIBRARY:
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageHasImageAttachment());
                break;
            case IMAGECAMERA:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageHasImageAttachment(),60);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageHasImageAttachment());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageHasImageStatus());
                break;
            case VIDEOLIBRARY:
                break;
            case VIDEOCAMERA:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestLocationMessage(),60);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestLocationMessage());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
                break;
            case VOICE:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestVoiceMessage(),60);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestVoiceMessage());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
                break;
            case LOCATION:
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestLocationMessage(),70);
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestLocationMessage());
                mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
                break;
            case PDF:
                break;
            case DOC:
                break;
            case PPT:
                break;
            case XCEL:
                break;
            default:
                break;
        }
    }

    @When("^I receive a \"([^\"]*)\" priority message from \"([^\"]*)\"$")
    public void iReceiveAPriorityMessageFrom(String messageType, String senderUser) throws Throwable {
        mobileWebdriver.data.addDictionary("messageType", messageType.trim().toLowerCase());
        mobileWebdriver.data.addDictionary("senderUser", senderUser.trim());
        String recipientUser = mobileWebdriver.data.getDictionary("loginUser");
        if("random".equalsIgnoreCase(messageType) || messageType.toLowerCase().contains("random")){
            String message = common.randomMessage(16);
            mobileWebdriver.data.addDictionary("message", message);
            if(messageType.toLowerCase().contains("everyone"))
            {
                recipientUser = "Everyone";
            }
            common.sendMessageToUserAPI(TestConfig.Environment.getUserToken(recipientUser), TestConfig.Environment.getOrgToken(), message, "1", senderUser);
        }
    }

    @Then("^I should see the priority message conversation style on the roster$")
    public void iShouldSeeThePriorityMessageConversationStyleOnTheRoster() throws Throwable {
        String senderUser = mobileWebdriver.data.getDictionary("senderUser");
        mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(conversationScreen.getRosterReceivePriority(), "$", senderUser));
    }
}