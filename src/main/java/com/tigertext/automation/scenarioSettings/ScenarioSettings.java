package com.tigertext.automation.scenarioSettings;
import com.api_wrapper.MobileAPI;
import com.tigertext.automation.config.TestConfig;
import com.tigertext.automation.pageObjects.LoginScreen;
import com.util.DriverFactory;
import com.util.WebDriverController;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.*;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

public class ScenarioSettings {
    @Autowired
    MobileAPI mobileWebdriver = new MobileAPI();
    WebDriverController webDriverController = new WebDriverController();
    LoginScreen loginPO;
    Logger logger = Logger.getLogger(ScenarioSettings.class.getName());


    /*This is responsible for any actions that take place BEFORE any test cases are executed.
      This is where we initialize which device/simulator to use*/
    @Before
    public void before(Scenario scenario) throws Throwable {
        //If the driver is null, create it. If not, reuse it so that we use only 1 simulator
        if(WebDriverController.appium_driver == null) {
            //Initializes the device/simulator
            AppiumDriver appium_driver = webDriverController.mobileInstantiate(scenario);
            loginPO = new LoginScreen(appium_driver);
        }
        else {
            WebDriverController.appium_driver.launchApp();
            loginPO = new LoginScreen(WebDriverController.appium_driver);
        }
        //This sets up which environment to use within the app
        theEnvironmentIsSetupToEnvironment();
    }

    //This is responsible for any actions that take place AFTER all test cases are executed
    @After
    public void after(Scenario scenario) throws Throwable {
        //Close the application
        WebDriverController.appium_driver.closeApp();

        //Below stores the results of whether the Scenario passed or failed.
        if(scenario.isFailed()){
            DriverFactory.passFailMap.put("didTestSuitePass" + scenario.getId(), false);
        }
        else {
            DriverFactory.passFailMap.put("didTestSuitePass" + scenario.getId(), true);
        }
    }

    public void theEnvironmentIsSetupToEnvironment() throws Throwable {
        String environment;
        //This makes sure that the environment is consistent per test even if the config file somehow changes.
        if(mobileWebdriver.doesDictionaryKeyExist("environment")){
            environment = mobileWebdriver.data.getDictionary("environment");
        }
        else {
            environment = TestConfig.Environment.getName();
        }
        mobileWebdriver.data.addDictionary("environment", environment);

        if(environment.toLowerCase().contains("prod")){
            environment = "Production";
        }
        else if(environment.toLowerCase().contains("stag")){
            environment = "Staging";
        }
        else if(environment.toLowerCase().contains("env")){
            environment = environment.replaceAll("^[a-z]{1}","E");
        }
        else if(environment.toLowerCase().contains("dev")){
            environment = "DEV";
        }

        logger.info("iOS environment: "+ environment);

        try {

            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getGetStartedButton(),60);
            loginPO.getGetStartedButton().click();
            loginPO.getNextKeyboardButton().click();
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getEnvironmentLabel(), 60);
            if (!loginPO.getEnvironmentLabel().getText().contains(environment)) {
                loginPO.getEnvironmentLabel().click();
                AppiumDriver<MobileElement> driver = WebDriverController.appium_driver;
                driver.findElements(MobileBy.className("XCUIElementTypePickerWheel")).get(0).sendKeys(environment);
                loginPO.getDonePickerButton().click();
            } else {
                mobileWebdriver.swipeBasedOnTwoElements(mobileWebdriver.returnWebElement(loginPO.getHelloText()), mobileWebdriver.returnWebElement(loginPO.getHelpButton()), "down");
            }
            mobileWebdriver.waitForPageObjectToBeClickable(loginPO.getNextKeyboardButton(),60);
            loginPO.getNextKeyboardButton().click();
        }catch (Exception e)
        {
            logger.info("NO NEED TO LOG IN");
        }
    }
}