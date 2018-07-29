package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethod;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.enums.AttachmentTypes;
import com.tigertext.automation.pageObjects.ConversationScreen;
import com.tigertext.automation.pageObjects.InboxScreen;
import com.tigertext.automation.pageObjects.MessageDetailsScreen;
import com.tigertext.automation.pageObjects.MessageReadByScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupMessage {
    MobileAPI mobileWebdriver = new MobileAPI();
    CommonMethod common  = new CommonMethod(mobileWebdriver);
    ConversationScreen conversationScreen = new ConversationScreen(WebDriverController.appium_driver);
    AttachmentTypes attachmentTypes;
    MessageP2P messageP2P = new MessageP2P();
    InboxScreen inboxScreen = new InboxScreen(WebDriverController.appium_driver);
    MessageDetailsScreen messageDetailsScreen = new MessageDetailsScreen(WebDriverController.appium_driver);
    MessageReadByScreen messageReadByScreen = new MessageReadByScreen(WebDriverController.appium_driver);
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:a");
    Date date = new Date();
    String time;
    Logger logger = Logger.getLogger(GroupMessage.class.getName());

    @Given("I have an existing recipient \"(.*)\"$")
    public void iHaveAnExistingRecipient(String group) throws Throwable {
        mobileWebdriver.data.addDictionary("recipientUser",group);
        common.navigateToConversationWithOutSearch(group);
//        common.navigateToConversation(group);

    }

    @Given("I send a random group message$")
    public void iSendARandomGroupMessage() throws Throwable {
        mobileWebdriver.data.addDictionary("messageType", "random");
        mobileWebdriver.data.addDictionary("messageStatus", "groupMessage");

        String message = common.randomMessage(16);
        mobileWebdriver.data.addDictionary("message", message);

        common.sendRandomMessage(message);
        mobileWebdriver.data.addDictionary("time", common.getCurrentTime());

    }

    @When("^I send a (.*) attachment to group \"([^\"]*)\"$")
    public void iSendATypeAttachmentTo(String messageType, String user) throws Throwable {

        String recipient = "";
        if(!"auto qa forum".equals(user.toLowerCase())) {
            recipient = "Auto Test Existing";
        }
        else{
            recipient = "Auto QA Forum";
        }
        mobileWebdriver.data.addDictionary("recipientUser", recipient);
        mobileWebdriver.data.addDictionary("messageType", messageType.trim().toLowerCase());
        attachmentTypes = AttachmentTypes.valueOf(messageType.replace(" ","").toUpperCase());
        switch(attachmentTypes){
            case IMAGELIBRARY:
                iAttachAnImageFromLibrary();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),60);
                conversationScreen.getSendButton().click();
                break;
            case IMAGECAMERA:
                common.takePhoto(false);
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),60);
                conversationScreen.getSendButton().click();
                break;
            case VIDEOLIBRARY:
                iAttachVideoFromLibrary();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),60);
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
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getFileAttachmentButton(),60);
                conversationScreen.getFileAttachmentButton().click();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getBrowseSelectedTab(),60);
                conversationScreen.getBrowseSelectedTab().click();
                mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getPdfDocument(),30);
                conversationScreen.getPdfDocument().click();
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

    @When("^I select \"([^\"]*)\" from the message options$")
    public void iOpenTheDetailsOfLatestMessage(String messageOption) throws Throwable {
        try {
            mobileWebdriver.data.addDictionary("messageStatus", messageOption);
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageStatus(),30);
            List<MobileElement> messageStatus = conversationScreen.getLatestMessagesStatus();
            mobileWebdriver.longPressElement(messageStatus.get(messageStatus.size() - 1));
            if(!messageOption.isEmpty()) {
                common.selectSentMessageOptions(messageOption);
            }
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @When("^I should (not )?see the id, date, and sender of the message$")
    public void iShouldSeeTheIdDateAndSenderOfTheMessage(String state) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        if(scriptState) {
            mobileWebdriver.waitForPageObjectToBeClickable(messageDetailsScreen.getMessageDetailsTitlePath(),30);
            assertMessageDetailsMessageId();
            assertMessageDetailsSenderToken();
            assertMessageDetailsDate();
        }
    }

    @When("^I select \"([^\"]*)\" to forward the message to$")
    public void iSelectToForwardTheMessageTo(String user) throws Throwable {
        try {
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","Select User"),30);
            mobileWebdriver.waitForPageObjectToBeClickable(inboxScreen.getEnterNameTextField(),30);
            inboxScreen.getEnterNameTextField().sendKeys(user);
            mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","Unselected"),40);
            mobileWebdriver.waitForElementAndClick(conversationScreen.getMessageOptionForwardButton(),30);
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @Then("^the message should (not )?be sent to \"([^\"]*)\"$")
    public void theMessageShouldBeSentTo(String state, String user) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String message = mobileWebdriver.data.getDictionary("message");
        if (scriptState) {
            String messageRecipient = mobileWebdriver.data.getDictionary("messageRecipient");
            String messageSender = mobileWebdriver.data.getDictionary("loginUser");
            if("everyone".equals(messageRecipient.toLowerCase())) {
                message = "[Everyone]: " + message;
            }
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getLatestMessageStatus(), 30);
            MobileElement latestForwardedMessage = mobileWebdriver.returnDynamicElement(conversationScreen.getDynamicLatestmessage(),"$",message);
            mobileWebdriver.assertTextIsPresent(latestForwardedMessage, message);
            MobileElement forwaredUser = mobileWebdriver.returnDynamicElement(InboxScreen.NEWMESSAGEUSERPATH,"$",messageSender);
            mobileWebdriver.assertTextIsPresent(forwaredUser,messageSender);
            MobileElement youForwarded = mobileWebdriver.returnDynamicElement(InboxScreen.NEWMESSAGEUSERPATH,"$","You Forwarded:");
            mobileWebdriver.assertTextIsPresent(youForwarded,"You Forwarded:");
            mobileWebdriver.assertPageObjectIsPresent(conversationScreen.getLatestMessageStatus());
        } else {
            try {
                mobileWebdriver.assertDynamicElementTextIsNotPresent(conversationScreen.getDynamicLatestmessage(),"$",message,user);
            }catch (TimeoutException e){
                logger.log(Level.INFO,e.toString());
            }
        }
    }

    @Then("^I should not see the recalled message$")
    public void iShouldNotSeeTheRecalledMessage() throws Throwable {
        String message = mobileWebdriver.data.getDictionary("message");
        List<MobileElement> conversationMessages = conversationScreen.getLatestMessage();
        if(!conversationMessages.isEmpty()) {
            mobileWebdriver.waitForPageObjectToBeClickable(conversationMessages.get(conversationMessages.size() - 1));
            mobileWebdriver.assertTextIsNotPresent(conversationMessages.get(conversationMessages.size() - 1), message);
        }
        else {
            Assert.assertTrue(conversationMessages.isEmpty());
        }
    }

    @When("^I open the message read by page for the latest message$")
    public void iOpenTheMessageReadByPageForTheLatestMessage() throws Throwable {
        MobileElement latestConversationMessageStatus = conversationScreen.getLatestMessageStatus();
        mobileWebdriver.waitForPageObjectToBeClickable(latestConversationMessageStatus,35);
        mobileWebdriver.waitForElementAndClick(latestConversationMessageStatus);
    }

    @Then("^I should see all of the users message status$")
    public void iShouldSeeAllOfTheUsersMessageStatus() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(messageReadByScreen.getMessageReadByTitle(),35);
        List <MobileElement> conversationGroupMemberReadStatus = messageReadByScreen.getMessageReadByUsers();
        for (MobileElement groupMemberStatus:conversationGroupMemberReadStatus) {
            mobileWebdriver.assertPageObjectIsPresent(groupMemberStatus);
        }
    }

    @And("^I select the \"([^\"]*)\" option$")
    public void iSelectTheQuickPickOption(String quickPickOption) throws Throwable {
        mobileWebdriver.waitForElementAndClick(conversationScreen.getQuickReplyButton(),15);
        common.selectQuickPick(quickPickOption);
    }

    @When("^I send the \"([^\"]*)\" option$")
    public void iSendTheQuickPickOption(String quickPickOption) throws Throwable {
        mobileWebdriver.data.addDictionary("messageType","quickReply");
        try {
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSendButton(),40);
            conversationScreen.getSendButton().click();
            time = dateFormat.format(date);
            mobileWebdriver.data.addDictionary("sentTime",time);
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @When("^I clear user conversations$")
    public void iClearUserConversations() throws Throwable{
        try {
            while (mobileWebdriver.elementExist(conversationScreen.getSentDeliveredStatus()))
                iOpenTheDetailsOfaMessage("recall");

        }catch (Exception e){
            logger.log(Level.INFO,e.toString());
        }
    }

    @Then("^I see no user conversations$")
    public void iSeeNoUserConversations() throws Throwable{
        mobileWebdriver.assertPageObjectIsNotPresent(conversationScreen.getSentDeliveredStatus());
    }


    private void assertMessageDetailsDate() throws Throwable {
        String messageDateDialogText = mobileWebdriver.returnText(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","Server Date:"));
        String messageDate = mobileWebdriver.returnParsedText(StaticData.messageDateRegex, messageDateDialogText);
        mobileWebdriver.data.addDictionary("messageDate", messageDate);
        if (messageDate.isEmpty()) {
            Assert.fail("Message Date does not exist in the Message Details Dialog!");
        }
        else{
            Assert.assertTrue(messageDateDialogText.contains(messageDate));
        }
    }

    private void assertMessageDetailsSenderToken() throws Throwable {
        String senderTokenDialogText = mobileWebdriver.returnText(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","Sender:"));
        String senderToken = mobileWebdriver.returnParsedText(StaticData.senderTokenRegex, senderTokenDialogText);
        mobileWebdriver.data.addDictionary("senderToken", senderToken);
        String userSenderToken = TestConfig.Environment.getUserSenderId(mobileWebdriver.data.getDictionary("loginUser"));
        Assert.assertTrue(senderToken.contains(userSenderToken));
    }

    private void assertMessageDetailsMessageId() throws Throwable {
        String messageDialogText = mobileWebdriver.returnText(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","ID:"));
        String messageId = mobileWebdriver.returnParsedText(StaticData.messageIdRegex, messageDialogText);
        mobileWebdriver.data.addDictionary("messageId", messageId);
        if (messageId.isEmpty()) {
            Assert.fail("Message ID does not exist in the Message Details Dialog!");
        }
    }

    private void iAttachVideoFromLibrary() {
        conversationScreen.getPhotoAttachmentButton().click();
        conversationScreen.getCameraRoll().click();
        mobileWebdriver.returnDynamicElement(conversationScreen.getCameraRollMediaType(), "$", "Video").click();
        if(mobileWebdriver.elementExist(conversationScreen.getAllowAlertButton(), 1)) mobileWebdriver.alertAccept();
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

    public void iOpenTheDetailsOfaMessage(String messageOption) throws Throwable {
        try {
            mobileWebdriver.data.addDictionary("messageStatus", messageOption);
            mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getSentDeliveredStatus(),30);
            List<MobileElement> messageStatus = conversationScreen.getSentDeliveredStatuses();
            mobileWebdriver.longPressElement(messageStatus.get(messageStatus.size() - 1));
            if(!messageOption.isEmpty()) {
                common.selectSentMessageOptions(messageOption);
            }
        }catch (NoSuchElementException e){
            logger.log(Level.INFO,e.toString());
        }
    }


}