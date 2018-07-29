package stepdef;

import com.api_wrapper.MobileAPI;
import com.tigertext.automation.common.CommonMethod;
import com.tigertext.automation.enums.AccountTypes;
import com.tigertext.automation.enums.InvalidDataTypes;
import com.tigertext.automation.pageObjects.InboxScreen;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.tigertext.automation.pageObjects.SettingsScreen;
import com.util.WebDriverController;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import com.tigertext.automation.config.TestConfig;
import java.util.logging.Logger;

import static com.tigertext.automation.common.StaticData.errorMessageStaticData;


public class Login {
	MobileAPI mobileWebdriver = new MobileAPI();
	CommonMethod common = new CommonMethod(mobileWebdriver);
	LoginScreen loginPO = new LoginScreen(WebDriverController.appium_driver);
    private InboxScreen inboxScreen = new InboxScreen(WebDriverController.appium_driver);
    private SettingsScreen settingsScreen = new SettingsScreen(WebDriverController.appium_driver);
    String email = "";
	String password = "";
	InvalidDataTypes invalidDataTypes;
	AccountTypes accountTypes;
	Logger logger = Logger.getLogger(Login.class.getName());

    @Given("The credentials for \"([^\"]*)\" are entered$")
    public void theCredentialsForUserIsEntered(String user) throws Throwable {
		String username = TestConfig.Environment.getUserEmail(user);
		String password = TestConfig.Environment.getUserPassword(user);
		common.enterEmail(username);
		common.enterPassword(password);
	}

	@Then("the verify your email button on the home screen is displayed$")
	public void verifyTheEmailButtonOnTheHomeScreenIsDisplayed(String state) throws Throwable {
		mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getVerifyYourEmailButton());
		mobileWebdriver.assertPageObjectIsPresent(loginPO.getVerifyYourEmailButton());
	}

	@Then("the user successfully logged in$")
	public void theUserSuccessfullyLoggedIn() throws Throwable {
		mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(), 5);
		mobileWebdriver.assertPageObjectIsPresent(loginPO.getInboxTab());
		mobileWebdriver.assertPageObjectIsPresent(loginPO.getGroupsTab());
	}

    @Given("^I am on the TT get started screen$")
    public void iAmOnTheTTGetStartedScreen() throws Throwable {
    	try {
			mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(), 60);
			mobileWebdriver.assertPageObjectIsPresent(loginPO.getGetStartedButton());
		}catch(Exception e)
		{
			logger.info("ALREADY LOGGED IN");

		}
    }

	@When("^I (do not )?enter the valid credentials for \"([^\"]*)\" on \"([^\"]*)\" device$")
	public void iEnterTheCredentialsForDeviceType(String state, String user, String deviceType) throws Throwable {
    	boolean scriptState = mobileWebdriver.scriptStateReturn(state);

    	try {
			if (scriptState) {
				email = TestConfig.Environment.getUserEmail(user);
				password = TestConfig.Environment.getUserPassword(user);
			} else {
				if ("invalid password".equals(user)) {
					email = TestConfig.Environment.getEmail();
					password = TestConfig.Environment.getInvalidPassword();
				} else
					email = TestConfig.Environment.getInvalidUserEmail(user);
			}

			mobileWebdriver.data.addDictionary("userType", user);
			mobileWebdriver.data.addDictionary("email", email);
			mobileWebdriver.data.addDictionary("password", password);

			mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(), 60);
			loginPO.getGetStartedButton().click();
			common.enterEmail(email);

			if (scriptState) {
				common.enterPassword(password);

				if ("sim".equalsIgnoreCase(deviceType)) {
					try {
						common.activatePhone();
					} catch (Exception e) {
					}
				} else {
					if (mobileWebdriver.elementExist(loginPO.getActivateButton(), 1)) {
						common.activatePhone();
					}
				}
			}
		}catch (Exception e)
		{
			logger.info("ALREADY LOGGED IN");

		}
	}

	@And("^I (do not )?accept the notification alerts$")
	public void acceptTheNotificationAlerts(String state) throws Throwable{
    	boolean scriptState = mobileWebdriver.scriptStateReturn(state);
        Thread.sleep(5000);
    	if(scriptState) {
    		try {
				mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getAllowAlertButton(), 155);
				common.allowNotificationFromLogin();
				common.allowNotifications();
				if (mobileWebdriver.elementExist(loginPO.getAllowAlertButton())) {
					common.allowNotificationFromLogin();
				}
			}catch (Exception e)
			{
				logger.info("ALREADY LOGGED IN");

			}
		}
	}

	@Then("^I should (not )?see the roster$")
	public void iShouldSeeTheRoster(String state) throws Throwable {
    	boolean scriptState = mobileWebdriver.scriptStateReturn(state);
		String userType = mobileWebdriver.data.getDictionary("userType");
    	if(scriptState) {
			if (mobileWebdriver.elementExist(loginPO.getClick2CallOn())) {
				loginPO.getClick2CallOn().click();
			}
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getInboxTab(),60);
			mobileWebdriver.assertPageObjectIsPresent(loginPO.getInboxTab());
		}else{
    		if(!"iosuser1".equals(userType.toLowerCase())) {
				invalidDataTypes = InvalidDataTypes.valueOf(userType.replace(" ", "").toUpperCase());
			}
			else{
    			invalidDataTypes = InvalidDataTypes.valueOf("EMPTY");
			}
			switch(invalidDataTypes){
				case INVALIDUSER:
					mobileWebdriver.assertTextIsPresent(loginPO.getCreatePassword(), errorMessageStaticData.get(userType.trim().toLowerCase()));
					break;
				case INVALIDFORMAT:
					mobileWebdriver.elementExist(loginPO.getInvalidFormat(), 5);
					mobileWebdriver.assertTextIsPresent(loginPO.getInvalidFormat(), errorMessageStaticData.get(userType.trim().toLowerCase()));
					break;
				case INVALIDPASSWORD:
					common.enterPassword(mobileWebdriver.data.getDictionary("password"));
					mobileWebdriver.assertTextIsPresent(loginPO.getInvalidPassword(), errorMessageStaticData.get(userType.trim().toLowerCase()));
					break;
				default:
					mobileWebdriver.assertPageObjectIsNotPresent(loginPO.getInboxTab());
			}
		}
//		iNavigateToTheSettingsPage();
//        iGoThroughTheSettingsProcess("log out");
	}

	@When("^I (do not )?enter the valid credentials for a (.*) account$")
	public void iLoginWithAAccountsAccount(String state, String accounts) throws Throwable {
		boolean scriptState = mobileWebdriver.scriptStateReturn(state);
		String account = accounts.trim().toLowerCase();
		mobileWebdriver.data.addDictionary("userType", account);
		if("saml".equalsIgnoreCase(account) || "usa and singapore".equalsIgnoreCase(account)){
			mobileWebdriver.data.addDictionary("email", TestConfig.Environment.getUserEmail(account));
		}else {
			mobileWebdriver.data.addDictionary("email", scriptState ? TestConfig.Environment.getUserEmail(account) : TestConfig.Environment.getInvalidEmail());
		}
		String username = mobileWebdriver.data.getDictionary("email");
		loginPO.getGetStartedButton().click();
		accountTypes = AccountTypes.valueOf(account.replace(" ","").toUpperCase());
		switch(accountTypes){
			case ADFS:
				break;
			case SAML:
				common.enterEmail(username);
				break;
			case USAANDSINGAPORE:
				if(scriptState) {
					common.loginWithSingapore(account, "USA");
				}else{
					common.enterEmail(username);
				}
				break;
			default:
				break;
		}
	}

	@When("^I (do not )?enter the email address for \"([^\"]*)\"$")
	public void iEnterTheEmailAddressFor(String state, String name) throws Throwable {
    	mobileWebdriver.data.addDictionary("userType", name);
		mobileWebdriver.data.addDictionary("email", mobileWebdriver.scriptStateReturn(state) ? TestConfig.Environment.getUserEmail(name) : TestConfig.Environment.getInvalidEmail());
		loginPO.getGetStartedButton().click();
    	common.enterEmail(mobileWebdriver.data.getDictionary("email"));
	}

	@And("^I click the forgot password link$")
	public void iClickTheForgotPasswordLink() throws Throwable {
		loginPO.getForgotPassword().click();
	}

	@Then("^I should (not)?see the forgot password page$")
	public void iShouldSeeTheForgotPasswordPageFor(String state) throws Throwable {
		String username = mobileWebdriver.data.getDictionary("email");
		if(mobileWebdriver.scriptStateReturn(state)) {
			mobileWebdriver.assertTextIsPresent(loginPO.getForgotPasswordCheck(), "Please check " + username + " for details");
		} else {
			mobileWebdriver.assertTextIsNotPresent(loginPO.getForgotPasswordCheck(), "Please check " + username + " for details");
		}
	}

	@And("^I (do not )?enter the original password for user by closing the forgot password page$")
	public void iEnterTheOriginalPasswordForUserByClosingTheForgotPasswordPage(String status) throws Throwable {
    	String name = mobileWebdriver.data.getDictionary("userType");
    	boolean scriptState = mobileWebdriver.scriptStateReturn(status);
		loginPO.getForgotPasswordClose().click();
		String password = scriptState ? TestConfig.Environment.getUserPassword(name) : TestConfig.Environment.getInvalidPassword();
		mobileWebdriver.elementExist(loginPO.getUserPasswordTextField(), 2);
		loginPO.getUserPasswordTextField().click();
		common.enterPassword(password);
		if(scriptState) {
			common.allowNotifications();
		}
	}

	@And("^I (do not )?enter valid authentication$")
	public void iGoThroughTheAccountAuthentication(String state) throws Throwable {
		boolean scriptState = mobileWebdriver.scriptStateReturn(state);
    	String account = mobileWebdriver.data.getDictionary("userType");
    	String username = mobileWebdriver.data.getDictionary("email");
		String password = scriptState ? TestConfig.Environment.getUserPassword(account) : TestConfig.Environment.getInvalidPassword();

		accountTypes = AccountTypes.valueOf(account.replace(" ","").toUpperCase());
		switch(accountTypes){
			case ADFS:
				break;
			case SAML:
				common.enterSAML(username, password);
				if(mobileWebdriver.elementExist(loginPO.getActivateButton(), 3)){
					common.activatePhone();
				}
				if(scriptState) {
					common.allowNotifications();
				}
				break;
			case USAANDSINGAPORE:
				if(scriptState) {
					common.allowNotifications();
				}else{
					mobileWebdriver.waitForElementAndClick(loginPO.getCancelButton());
				}
				break;
			default:
				throw new Exception("Unable to find the parameter for the Scenario Settings");
		}
	}

	@And("^I go through the settings \"([^\"]*)\" process$")
	public void iGoThroughTheSettingsProcess(String settingsOption) throws Throwable {
    	common.selectSettingsOption(settingsOption);
	}

    @And("^I navigate to the settings page")
    public void iNavigateToTheSettingsPage() throws Throwable {
       common.navigateToTheSettingsPage();
    }


}