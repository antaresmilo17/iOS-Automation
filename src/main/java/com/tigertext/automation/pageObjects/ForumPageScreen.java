package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class ForumPageScreen {
    public ForumPageScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String FORUMPATH = "//*[contains(@label,'$')]";
    public static final String JOINFORUMICONPATH = "//*[@label='Join']";
    public static final String DYNAMICFORUMDETAILSBUTTONPATH = "//*[contains(@label,'$ details')]";
    public static final String FORUMSETTINGSLEAVEFORUMBUTTONPATH = "Leave this Forum";
    public static final String CELEBRATIONSFORUMPATH = "//*[contains(@label,'Celebrations')]";
    public static final String UNMUTEOBJECTFORUMPATH = "//*[contains(@label,'Unmute')]";
    public static final String FORUMSETTINGSUNMUTEDBUTTONPATH ="//*[@label='Mute' and @value='0']";
    public static final String FORUMSETTINGSMUTEDBUTTONPATH ="//*[@label='Mute' and @value='1']";

    @iOSFindBy(xpath = JOINFORUMICONPATH)
    private MobileElement joinForumIcon;
    @iOSFindBy(accessibility = FORUMSETTINGSLEAVEFORUMBUTTONPATH)
    private MobileElement forumSettingsLeaveForumButton;
    @iOSFindBy(xpath = FORUMSETTINGSUNMUTEDBUTTONPATH)
    private MobileElement forumSettingsUnmutedForumButton;
    @iOSFindBy(xpath = FORUMSETTINGSMUTEDBUTTONPATH)
    private MobileElement forumSettingsMutedForumButton;
    @iOSFindBy(xpath = CELEBRATIONSFORUMPATH)
    private MobileElement celerationsForum;
    @iOSFindBy(xpath = UNMUTEOBJECTFORUMPATH)
    private MobileElement unmuteForumObject;

    public MobileElement getCelerationsForum() {
        return celerationsForum;
    }
    public MobileElement getForumSettingsUnmutedForumButton() {
        return forumSettingsUnmutedForumButton;
    }
    public MobileElement getForumSettingsMutedForumButton() {
        return forumSettingsMutedForumButton;
    }
    public MobileElement getJoinForumIcon() {
        return joinForumIcon;
    }
    public MobileElement getForumSettingsLeaveForumButton() {
        return forumSettingsLeaveForumButton;
    }
    public MobileElement getUnmuteForumObject() {
        return unmuteForumObject;
    }
}
