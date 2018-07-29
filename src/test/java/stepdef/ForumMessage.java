package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethod;
import com.tigertext.automation.common.StaticData;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.enums.AttachmentTypes;
import com.tigertext.automation.pageObjects.*;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ForumMessage {
    MobileAPI mobileWebdriver = new MobileAPI();
    CommonMethod common  = new CommonMethod(mobileWebdriver);
    ConversationScreen conversationScreen = new ConversationScreen(WebDriverController.appium_driver);
    AttachmentTypes attachmentTypes;
    MessageP2P messageP2P = new MessageP2P();
    ForumPageScreen forumPageScreen = new ForumPageScreen(WebDriverController.appium_driver);
    GroupMessage groupMessage = new GroupMessage();
    LoginScreen loginPO = new LoginScreen(WebDriverController.appium_driver);
    SettingsScreen settingsScreen = new SettingsScreen(WebDriverController.appium_driver);
    CreateForumScreen createForumScreen = new CreateForumScreen(WebDriverController.appium_driver);

    InboxScreen inboxScreen = new InboxScreen(WebDriverController.appium_driver);
    MessageDetailsScreen messageDetailsScreen = new MessageDetailsScreen(WebDriverController.appium_driver);
    MessageReadByScreen messageReadByScreen = new MessageReadByScreen(WebDriverController.appium_driver);
    SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:a");
    Date date = new Date();
    String time;
    String addMembers = "false";
    Logger logger = Logger.getLogger(ForumMessage.class.getName());

    @Given("I have an existing forum \"(.*)\"$")
    public void iHaveAnExistingForum(String group) throws Throwable {
        String chat = "";
        if("random".equals(group.toLowerCase()))
        {
            chat = mobileWebdriver.data.getDictionary("forumName");

        }else
        {
            chat = group;
        }
        mobileWebdriver.data.addDictionary("recipientUser",chat);
        common.navigateToConversation(chat);

    }


    @When("^I send a (.*) attachment to forum \"([^\"]*)\"$")
    public void iSendATypeAttachmentToForume(String messageType, String user) throws Throwable {
        groupMessage.iSendATypeAttachmentTo(messageType,user);
    }

    @When("^I navigate to the \"([^\"]*)\" page$")
    public void iNavigateToThePage(String page) throws Throwable {
        switch(page) {
            case "forums":
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGroupsTab(), 20);
                loginPO.getGroupsTab().click();
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICGROUPSECTIONSPATH, "$", "Groups"), 20);
                mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICGROUPSECTIONSPATH, "$", "Forums").click();
                break;
            case "inbox":
                mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(),20);
                loginPO.getInboxTab().click();
                break;
            default:
                break;
        }

    }

    @When("^I should see the (.*) section$")
    public void iShouldSeeTheSection(String section) throws Throwable {
        if("my forums".equals(section)) {
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "MY FORUMS"), 20);
            mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "MY FORUMS"));
        }
        else{
            mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "EXPLORE FORUMS"), 20);
            mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "EXPLORE FORUMS"));
        }
    }

    @When("^I create a new forum with name \"([^\"]*)\"$")
    public void iCreateAnewForumWithName(String forumName) throws Throwable {
        String forumNameToUse = "";
        if("random".equals(forumName.toLowerCase())) {
            forumNameToUse = common.randomMessage(16);
        }
        else{
            forumNameToUse = forumName;
        }
        mobileWebdriver.data.addDictionary("forumName",forumNameToUse);
        iNavigateToThePage("forums");
        iEnterNewForumInformation(forumNameToUse);
        iAddUserToForum("Auto Admin");
        createForumScreen.getCreateNewForumButton().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getBackButton(),60);
        if(!"message forum".equals(forumName.toLowerCase())) {
            conversationScreen.getBackButton().click();
        }
    }

    @When("^I should (not )?see the forum in the \"([^\"]*)\" page$")
    public void iShouldSeeTheForumInTheForumsPage(String state, String location) throws Throwable {
        boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        String forumName = mobileWebdriver.data.getDictionary("forumName");
        if(scriptState) {
            if("forums".equals(location.toLowerCase())) {
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "MY FORUMS"), 60);
                mobileWebdriver.elementExist(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName));
                iNavigateToThePage("inbox");
                iLeaveForum(forumName);
            }
            else if("roster".equals(location.toLowerCase())){
                iNavigateToThePage("inbox");
                mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName),20);
                mobileWebdriver.elementExist(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName));
                iLeaveForum(forumName);
            }
        }else{
            mobileWebdriver.assertPageObjectIsNotPresent(forumPageScreen.getCelerationsForum());
        }
    }

    @When("^I join a forum \"([^\"]*)\" from the forums page$")
    public void iJoinAforumFromTheForumsPage(String forumName) throws Throwable {
        mobileWebdriver.data.addDictionary("forumName",forumName);
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "MY FORUMS"), 60);
        mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName).click();
        mobileWebdriver.waitForPageObjectToBeClickable(forumPageScreen.getJoinForumIcon(),20);
        forumPageScreen.getJoinForumIcon().click();
        mobileWebdriver.waitForPageObjectToBeClickable(conversationScreen.getBackButton(),20);
        conversationScreen.getBackButton().click();
    }

    @When("^I leave forum \"([^\"]*)\"$")
    public void iLeaveForum(String forumName) throws Throwable {
        mobileWebdriver.data.addDictionary("forumName",forumName);
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName), 60);
        mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", forumName).click();
        mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(ForumPageScreen.DYNAMICFORUMDETAILSBUTTONPATH, "$", forumName));
        mobileWebdriver.waitForElementAndClick(forumPageScreen.getForumSettingsLeaveForumButton(),20);
        mobileWebdriver.waitForElementAndClick(settingsScreen.getLogoutYesButtonPath(),20);
    }

    @When("^I open the \"([^\"]*)\" details option$")
    public void iOpenTheForumDetailsOption(String forumName) throws Throwable {
        String chat = "";
        if("random".equals(forumName.toLowerCase()))
        {
            chat =  mobileWebdriver.data.getDictionary("forumName");

        }
        else
        {
            chat = forumName;
        }
        mobileWebdriver.data.addDictionary("forumName",chat);
        mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(ForumPageScreen.DYNAMICFORUMDETAILSBUTTONPATH, "$", chat));

    }

    @When("^I select the \"([^\"]*)\" option from the forum settings$")
    public void iSelectTheOptionFromTheForumSettins(String forumName) throws Throwable {
        switch (forumName.toLowerCase()){
            case "mute":
                try {
                    forumPageScreen.getForumSettingsMutedForumButton().click();
                }
                catch (TimeoutException e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }
                catch (NoSuchElementException e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }catch (Exception e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }
                mobileWebdriver.waitForElementAndClick(forumPageScreen.getForumSettingsUnmutedForumButton(),30);
                break;
            case "unmute":
                try {
                    forumPageScreen.getForumSettingsUnmutedForumButton().click();
                }
                catch (TimeoutException e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }
                catch (NoSuchElementException e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }catch (Exception e)
                {
                    logger.log(Level.INFO,"Log Message: "+ e);
                }
                mobileWebdriver.waitForElementAndClick(forumPageScreen.getForumSettingsMutedForumButton(),30);
                break;
            case "add members":
                mobileWebdriver.data.addDictionary("addMember","true");
                mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH, "$", "Add Members"));
                iAddUserToForum("IOSQa1");
                break;
            default:
                break;
        }
    }

    @When("^I mute the conversation for (.*)$")
    public void iMuteTheConversationFor(String muteDuration) throws Throwable {
        mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(ForumPageScreen.FORUMPATH,"$",muteDuration),20);
    }

    @When("^I should see the conversation is (not )?muted$")
    public void iShouldSeeTheConversationIsMuted(String state) throws Throwable {
        boolean scriptstate = mobileWebdriver.scriptStateReturn(state);
        if(scriptstate) {
            mobileWebdriver.waitForPageObjectToBeClickable(forumPageScreen.getForumSettingsMutedForumButton(), 30);
            mobileWebdriver.assertPageObjectIsPresent(forumPageScreen.getForumSettingsMutedForumButton());
        }
        else
        {
            mobileWebdriver.assertPageObjectIsNotPresent(forumPageScreen.getForumSettingsMutedForumButton());
        }
    }

    @When("^I should see the member added to the forum$")
    public void iShouldSeeTheMemberAddedToTheForum() throws Throwable {
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "Add"));
        mobileWebdriver.assertPageObjectIsPresent(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "IOSQa1"));
        mobileWebdriver.waitForElementAndClick(conversationScreen.getBackButton());
        mobileWebdriver.waitForElementAndClick(forumPageScreen.getForumSettingsLeaveForumButton(),20);
        mobileWebdriver.waitForElementAndClick(settingsScreen.getLogoutYesButtonPath(),20);
    }

    private void iAddUserToForum(String user) {
        addMembers = mobileWebdriver.data.getDictionary("addMember");
        if(addMembers.equals("true")){
            mobileWebdriver.waitForPageObjectToBeClickable(createForumScreen.getAddMembersToTextField(),60);
            createForumScreen.getAddMembersToTextField().sendKeys(user);

        }
        else {
            mobileWebdriver.waitForPageObjectToBeClickable(inboxScreen.getEnterNameTextField(), 60);
            inboxScreen.getEnterNameTextField().clear();
            inboxScreen.getEnterNameTextField().sendKeys(user);
        }
        mobileWebdriver.waitForElementAndClick(mobileWebdriver.returnDynamicElement(MessageDetailsScreen.DYNAMICMESSAGEDETAILSITEMPATH,"$","Unselected"),60);
    }

    private void iEnterNewForumInformation(String forumName) {
        mobileWebdriver.waitForPageObjectToBeClickable(mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "Create Forum Button"), 60);
        mobileWebdriver.returnDynamicElement(GroupsScreen.DYNAMICCONTAINSGROUPSECTIONPATH, "$", "Create Forum Button").click();
        createForumScreen.getEnterNewForumNameTextField().clear();
        createForumScreen.getEnterNewForumNameTextField().sendKeys(forumName);
        createForumScreen.getEnterNewForumDescriptionTextField().sendKeys("Auto Description");
        loginPO.getNextButton().click();
    }

}