package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethod;
import com.tigertext.automation.enums.AttachmentTypes;
import com.tigertext.automation.pageObjects.*;
import com.util.WebDriverController;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.TimeoutException;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EveryoneMessage {
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
    Logger logger = Logger.getLogger(EveryoneMessage.class.getName());

    @Given("I send app to background$")
    public void iHaveAnExistingGroup() throws Throwable {
        WebDriverController.appium_driver.runAppInBackground(Duration.ofMinutes(1));
        ProcessBuilder pb = new ProcessBuilder("idevicedebug", "run", "TigerText");
        Thread.sleep(5000);
        Process p=pb.start();
        Thread.sleep(5000);
        p.destroy();
    }


}