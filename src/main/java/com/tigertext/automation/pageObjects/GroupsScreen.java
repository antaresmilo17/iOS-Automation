package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class GroupsScreen {
    public GroupsScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String DYNAMICGROUPSECTIONSPATH = "//*[@label='$']";
    public static final String DYNAMICCONTAINSGROUPSECTIONPATH = "//*[contains(@label,'$')]";


    @iOSFindBy(xpath = "")
    private MobileElement searchBar;


}
