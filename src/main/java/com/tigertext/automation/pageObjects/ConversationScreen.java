package com.tigertext.automation.pageObjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;

import javax.print.DocFlavor;
import java.util.List;

public class ConversationScreen {
    public ConversationScreen(AppiumDriver<MobileElement> driver){
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public static final String BACKBUTTONPATH = "Back button";
    public static final String BACKBUTTONPATH2 = "Back Button";
    public static final String AVATARPATH = "//*[contains(@label, ' details')]";
    public static final String MESSAGETEXTFIELDPATH = "Text Field";
    public static final String ALLOWALERTBUTTONPATH1 = "Allow";
    public static final String ALLOWALERTBUTTONPATH2 = "OK";
    public static final String ALLOWALERTBUTTONPATH3 = "Proceed";
    public static final String QUICKREPLYOFFBUTTONPATH = "iconQuickreplyOff";
    public static final String QUICKREPLYONBUTTONPATH = "iconQuickreplyOn";
    public static final String QUICKREPLYEDITBUTTONPATH = "Edit";
    public static final String PMOFFBUTTONPATH = "iconPmOff";
    public static final String PMONBUTTONPATH = "iconPmOn";
    public static final String PHOTOATTACHMENTBUTTONOFFPATH = "iconPicOff";
    public static final String PHOTOATTACHMENTBUTTONONPATH = "iconPicOn";
    public static final String CAMERAATTACHENTBUTTONONPATH = "iconCameraON";
    public static final String CAMERAATTACHENTBUTTONOFFPATH = "iconCameraOff";
    public static final String LOCATIONATTACHMENTOFFBUTTONPATH = "iconLocationOff";
    public static final String LOCATIONATTACHMENTONBUTTONPATH = "iconLocationOn";
    public static final String FILEATTACHMENTOFFBUTTONPATH = "iconFileOff";
    public static final String FILEATTACHMENTONBUTTONPATH = "iconFileOn";
    public static final String VOICEATTACHMENTOFFBUTTONPATH = "iconMicOff";
    public static final String VOICEATTACHMENTONBUTTONPATH = "iconMicOn";
    public static final String SENDBUTTONPATH = "Send Button";
    public static final String LATESTMESSAGEISPRIORITYPATH = "Latest Message Priority";
    public static final String SENTSTATUSPATH = "Sent";
    public static final String DELIVEREDSTATUSPATH = "Delivered";
    public static final String READSTATUSPATH = "//*[contains(@label,'Read')]";
    public static final String SENDMESSAGEEMPTYSTATEBUTTONPATH = "Welcome to TigerText. Let's send your first secure message.";
    public static final String DYNAMICLATESTMESSAGE = "//XCUIElementTypeCell[@name='Latest Message']//*[@value='$']";
    public static final String LATESTMESSAGE = "//XCUIElementTypeCell[@name='Latest Message']";
    public static final String DYNAMICMESSAGEVALUE = "//*[@value='$']";
    public static final String LATESTMESSAGEHASIMAGEPRIORITYPATH = "Latest Message hasImageAttachment Priority";
    public static final String LATESTMESSAGEPRIORITYPATH = "Latest Message Priority";
    public static final String LATESTVOICEMESSAGEPATH = "//XCUIElementTypeCell[@name='Latest Message']//*[@label='play']";
    public static final String LATESTLOCATIONMESSAGEPATH = "//XCUIElementTypeCell[@name='Latest Message']//XCUIElementTypeImage";
    public static final String LATESTMESSAGESTATUSPATH = "//XCUIElementTypeCell[@name='Latest Message']//*[@label='Sent' or @label='Delivered']";
    public static final String LATESTMESSAGEPRIORITYSTATUSPATH = "//XCUIElementTypeCell[@name='Latest Message Priority']//*[@label='Sent' or @label='Delivered']";
    public static final String LATESTMESSAGEPRIORITYHASIMAGESTATUSPATH = "//XCUIElementTypeCell[@name='Latest Message hasImageAttachment Priority']//*[@label='Sent' or @label='Delivered']";
    public static final String LATESTMESSAGEHASIMAGESTATUSPATH = "//XCUIElementTypeCell[@name='Latest Message hasImageAttachment']//*[@label='Sent' or @label='Delivered']";
    public static final String CAMERAROLLPATH = "Camera Roll";
    public static final String ALLPHOTOSPATH = "All Photos";
    public static final String PHOTOSPATH = "Photo,";
    public static final String CAMERAROLLMEDIATYPE = "//*[contains(@label,'$')]";
    public static final String CAMERAROLLIMAGETRAITPATH = "//*[@type='XCUIElementTypeOther']";
    public static final String CAMERAVIEWFRONTFACINGPATH = "Front facing";
    public static final String CAMERAVIEWBACKFACINGPATH = "Back facing";
    public static final String DYNAMICCAMERAMODEVIDEO = "//*[@label='Camera Mode' and @value='$']";
    public static final String CAMERAMODEPHOTOPATH = "//*[@value='photo']";
    public static final String CONVERSATIONRECEIVEPRIORITY1 = "//*[@value='$']/preceding-sibling::*[@label='PRIORITY MESSAGE']";
    public static final String CONVERSATIONRECEIVEPRIORITY2 = "//*[@value='$']/following-sibling::*[@label='PRIORITY MESSAGE']";
    public static final String ROSTERRECEIVEPRIORITY = "//*[contains(@label,'$')][1]//*[@label='PRIORITY']";
    public static final String ROSTERRECEIVEMESSAGE = "//*[@label='user']/following-sibling::*[@label='$']";
    public static final String ROSTERMESSAGE = "//*[@label='$']";
    public static final String DYNAMICQUICKPICKMESSAGEVALUE = "//XCUIElementTypeOther/descendant::XCUIElementTypeCell[@name='Latest Message']/descendant::*[@type='XCUIElementTypeStaticText']";
    public static final String CAMERAMODEPATH = "Camera Mode";
    public static final String CAMERARECORDVIDEOBUTTONPATH = "Record Video";
    public static final String CAMERASTOPRECORDINGVIDEOBUTTONPATH = "Stop Recording Video";
    public static final String CAMERATAKEPHOTOBUTTONPATH = "Take Picture";
    public static final String CAMERAUSEPHOTOPATH = "Use Photo";
    public static final String CAMERAUSEVIDEOPATH = "Use Video";
    public static final String VOICERECORDSTARTSTOPPATH = "recordOff";
    public static final String VOICERECORDSTOPPATH = "stop";
    public static final String VOICERECORDRECORDINGTEXTPATH = "Recording...";
    public static final String LOCATIONINPROGRESSINDICATORPATH = "In progress";
    public static final String LATESTMESSAGEHASIMAGEATTACHMENTPATH = "Latest Message hasImageAttachment";
    public static final String LIBRARYCHOOSEVIDEOPATH = "Choose";
    public static final String LASTMESSAGEIMAGEPATH = "//XCUIElementTypeTable/XCUIElementTypeAny[3]/XCUIElementTypeAny[2]";
    public static final String IMAGEOBJECTPATH = "Image";
    public static final String SENDONPATH = "send_on";
    public static final String CLOSEMARKUPPATH = "close markup";
    public static final String BROWSESELECTEDTABPATH = "Browse";
    public static final String ICLOUDLOCATIONBUTTONPATH = "iCloud Drive, level 1";
    public static final String PDFDOCUMENTFILEPATH = "pdfFile, 1/23/18, 9 KB";
    public static final String DOCDOCUMENTFILEPATH = "docFile, 1/23/18, 9 KB";
    public static final String PPDOCUMENTFILEPATH = "powerPointFile, 1/23/18, 72 KB";
    public static final String XLSDOCUMENTFILEPATH = "xlsFile, 1/23/18, 7 KB";
    public static final String MESSAGEOPTIONSRESENDPATH = "Resend";
    public static final String MESSAGEOPTIONSRESENDASPRIORITYPATH = "Resend as priority";
    public static final String MESSAGEOPTIONSRECALLPATH = "Recall";
    public static final String MESSAGEOPTIONSFORWARDPATH = "Forward";
    public static final String MESSAGEOPTIONSDETAILSPATH = "Details";
    public static final String MESSAGEOPTIONSSHOWMEMOREITEMSPATH = "Show more items";
    public static final String FIRSTQUICKREPLYOPTIONPATH = "I'll be right there";
    public static final String SECONDQUICKREPLYOPTIONPATH ="I'll get back to you ASAP";
    public static final String THIRDQUICKREPLYOPTIONPATH = "Situation: I need to talk to you about the patient in room #";
    public static final String EMOJIKEYBOARDPATH = "Next keyboard";
    public static final String REDBALLOONEMOJIPATH = "\uD83C\uDF88";
    public static final String LOLEMOJIPATH = "\uD83D\uDE02";
    public static final String BLOWKISSEMOJIPATH = "\uD83D\uDE18";

//    @iOSFindBy(accessibility = BACKBUTTONPATH)
//    private MobileElement backButton;
    @iOSFindAll({@iOSBy(accessibility = BACKBUTTONPATH), @iOSBy(accessibility = BACKBUTTONPATH2)})
    private MobileElement backButton;
    @iOSFindBy(accessibility = EMOJIKEYBOARDPATH)
    private MobileElement emojiKeyboardButton;
    @iOSFindBy(accessibility = REDBALLOONEMOJIPATH)
    private MobileElement redBalloonEmojiButton;
    @iOSFindBy(accessibility = LOLEMOJIPATH)
    private MobileElement lolEmojiButton;
    @iOSFindBy(accessibility = BLOWKISSEMOJIPATH)
    private MobileElement blowKissEmojiButton;
    @iOSFindBy(xpath = DYNAMICQUICKPICKMESSAGEVALUE)
    private List<MobileElement> quickPickText;
    @iOSFindBy(accessibility = FIRSTQUICKREPLYOPTIONPATH)
    private MobileElement firstQuickReplyOption;
    @iOSFindBy(accessibility = SECONDQUICKREPLYOPTIONPATH)
    private MobileElement secondQuickReplyOption;
    @iOSFindBy(accessibility = THIRDQUICKREPLYOPTIONPATH)
    private MobileElement thirdQuickReplyOption;
    @iOSFindBy(accessibility = MESSAGEOPTIONSRESENDPATH)
    private MobileElement messageOptionResendButton;
    @iOSFindBy(accessibility = MESSAGEOPTIONSRECALLPATH)
    private MobileElement messageOptionRecallButton;
    @iOSFindBy(accessibility = MESSAGEOPTIONSFORWARDPATH)
    private MobileElement messageOptionForwardButton;
    @iOSFindBy(accessibility = MESSAGEOPTIONSRESENDASPRIORITYPATH)
    private MobileElement messageOptionResendAsPriorityButton;
    @iOSFindBy(accessibility = MESSAGEOPTIONSDETAILSPATH)
    private MobileElement messageOptionDetailsButton;
    @iOSFindBy(accessibility = MESSAGEOPTIONSSHOWMEMOREITEMSPATH)
    private MobileElement messageOptionShowMeMoreItemsButton;
    @iOSFindBy(accessibility = PDFDOCUMENTFILEPATH)
    private MobileElement pdfDocument;
    @iOSFindBy(accessibility = PPDOCUMENTFILEPATH)
    private MobileElement ppDocument;
    @iOSFindBy(accessibility = DOCDOCUMENTFILEPATH)
    private MobileElement docDocument;
    @iOSFindBy(accessibility = XLSDOCUMENTFILEPATH)
    private MobileElement xlsDocument;
    @iOSFindBy(accessibility = ICLOUDLOCATIONBUTTONPATH)
    private MobileElement iCloudLocationButton;
    @iOSFindBy(accessibility = BROWSESELECTEDTABPATH)
    private MobileElement browseSelectedTab;
    @iOSFindBy(xpath = CAMERAMODEPHOTOPATH)
    private MobileElement cameraModeVideo2;

    @iOSFindBy(xpath = AVATARPATH)
    private MobileElement avatar;

    @iOSFindBy(accessibility = MESSAGETEXTFIELDPATH)
    private MobileElement messageTextField;

    @iOSFindAll({
            @iOSBy(accessibility = ALLOWALERTBUTTONPATH1),
            @iOSBy(accessibility = ALLOWALERTBUTTONPATH2),
            @iOSBy(accessibility = ALLOWALERTBUTTONPATH3)
    })
    private MobileElement allowAlertButton;

    @iOSFindAll({@iOSBy(accessibility = QUICKREPLYOFFBUTTONPATH), @iOSBy(accessibility = QUICKREPLYONBUTTONPATH)})
    private MobileElement quickReplyButton;
    @iOSFindBy(accessibility = QUICKREPLYEDITBUTTONPATH)
    private MobileElement quickReplyEditButton;
    @iOSFindAll({@iOSBy(accessibility = PMOFFBUTTONPATH), @iOSBy(accessibility = PMONBUTTONPATH)})
    private MobileElement pmButton;
    @iOSFindAll({@iOSBy(accessibility = PHOTOATTACHMENTBUTTONOFFPATH), @iOSBy(accessibility = PHOTOATTACHMENTBUTTONONPATH)})
    private MobileElement photoAttachmentButton;
    @iOSFindAll({@iOSBy(accessibility = CAMERAATTACHENTBUTTONOFFPATH), @iOSBy(accessibility = CAMERAATTACHENTBUTTONONPATH)})
    private MobileElement cameraAttachmentButton;
    @iOSFindAll({@iOSBy(accessibility = LOCATIONATTACHMENTOFFBUTTONPATH), @iOSBy(accessibility = LOCATIONATTACHMENTONBUTTONPATH)})
    private MobileElement locationAttachmentButton;
    @iOSFindAll({@iOSBy(accessibility = FILEATTACHMENTOFFBUTTONPATH), @iOSBy(accessibility = FILEATTACHMENTONBUTTONPATH)})
    private MobileElement fileAttachmentButton;
    @iOSFindAll({@iOSBy(accessibility = VOICEATTACHMENTOFFBUTTONPATH), @iOSBy(accessibility = VOICEATTACHMENTONBUTTONPATH)})
    private MobileElement voiceAttachmentButton;
    @iOSFindBy(accessibility = SENDBUTTONPATH)
    private MobileElement sendButton;
    @iOSFindBy(accessibility = LATESTMESSAGEISPRIORITYPATH)
    private MobileElement latestMessageIsPriority;
    @iOSFindAll({@iOSBy(accessibility = SENTSTATUSPATH), @iOSBy(accessibility = DELIVEREDSTATUSPATH)})
    private MobileElement sentDeliveredStatus;
    @iOSFindAll({@iOSBy(accessibility = SENTSTATUSPATH), @iOSBy(accessibility = DELIVEREDSTATUSPATH)})
    private List<MobileElement> sentDeliveredStatuses;
    @iOSFindBy(accessibility = SENDMESSAGEEMPTYSTATEBUTTONPATH)
    private MobileElement sendMessageEmptyStateButton;
    @iOSFindBy(accessibility = LATESTMESSAGEHASIMAGEPRIORITYPATH)
    private MobileElement latestMessageHasImagePriority;
    @iOSFindBy(accessibility = LATESTMESSAGEPRIORITYPATH)
    private MobileElement latestMessagePriority;
    @iOSFindBy(xpath = LATESTVOICEMESSAGEPATH)
    private MobileElement latestVoiceMessage;
    @iOSFindBy(xpath = LATESTLOCATIONMESSAGEPATH)
    private MobileElement latestLocationMessage;
    @iOSFindBy(xpath = LATESTMESSAGESTATUSPATH)
    private MobileElement latestMessageStatus;
    @iOSFindBy(xpath = LATESTMESSAGESTATUSPATH)
    private List<MobileElement> latestMessagesStatus;
    @iOSFindBy(xpath = LATESTMESSAGEPRIORITYSTATUSPATH)
    private MobileElement latestMessagePriorityStatus;
    @iOSFindBy(xpath = LATESTMESSAGEPRIORITYHASIMAGESTATUSPATH)
    private MobileElement latestMessagePriorityHasImageStatus;
    @iOSFindBy(xpath = LATESTMESSAGEHASIMAGESTATUSPATH)
    private MobileElement latestMessageHasImageStatus;
    @iOSFindBy(accessibility = CAMERAROLLPATH)
    private MobileElement cameraRoll;
    @iOSFindBy(accessibility = PHOTOSPATH)
    private List<MobileElement> photos;
    @iOSFindBy(accessibility = ALLPHOTOSPATH)
    private MobileElement allPhotosPath;
    @iOSFindAll({@iOSBy(accessibility = CAMERAVIEWFRONTFACINGPATH), @iOSBy(accessibility = CAMERAVIEWBACKFACINGPATH)})
    private MobileElement cameraViewBackFacing;
    @iOSFindBy(accessibility = CAMERAMODEPATH)
    private MobileElement cameraMode;
    @iOSFindBy(accessibility = CAMERARECORDVIDEOBUTTONPATH)
    private MobileElement cameraRecordVideoButton;
    @iOSFindBy(accessibility = CAMERASTOPRECORDINGVIDEOBUTTONPATH)
    private MobileElement cameraStopRecordingVideoButton;
    @iOSFindBy(accessibility = CAMERATAKEPHOTOBUTTONPATH)
    private MobileElement cameraTakePhotoButton;
    @iOSFindBy(accessibility = CAMERAUSEPHOTOPATH)
    private MobileElement cameraUsePhoto;
    @iOSFindBy(accessibility = CAMERAUSEVIDEOPATH)
    private MobileElement cameraUseVideo;
    @iOSFindBy(accessibility = VOICERECORDSTARTSTOPPATH)
    private MobileElement voiceRecordStartStop;
    @iOSFindBy(accessibility = VOICERECORDSTOPPATH)
    private MobileElement voiceRecordStop;
    @iOSFindBy(accessibility = VOICERECORDRECORDINGTEXTPATH)
    private MobileElement voiceRecordRecordingText;
    @iOSFindBy(accessibility = LOCATIONINPROGRESSINDICATORPATH)
    private MobileElement locationInProgressIndicator;
    @iOSFindBy(accessibility = LATESTMESSAGEHASIMAGEATTACHMENTPATH)
    private MobileElement latestMessageHasImageAttachment;
    @iOSFindBy(accessibility = LIBRARYCHOOSEVIDEOPATH)
    private MobileElement libraryChooseVideo;
    @iOSFindBy(xpath = LASTMESSAGEIMAGEPATH)
    private MobileElement lastMessageImage;
    @iOSFindBy(accessibility = IMAGEOBJECTPATH)
    private MobileElement imageObject;
    @iOSFindBy(accessibility = SENDONPATH)
    private MobileElement sendOn;
    @iOSFindBy(accessibility = CLOSEMARKUPPATH)
    private MobileElement closeMarkup;
    @iOSFindBy(xpath = CAMERAROLLIMAGETRAITPATH)
    private List<MobileElement> cameraRollImageTrait;
    @iOSFindBy(xpath = LATESTMESSAGE)
    private List<MobileElement> latestMessage;
    public List<MobileElement> getCameraRollImageTrait() {
        return cameraRollImageTrait;
    }
    public MobileElement getBackButton() {
        return backButton;
    }
    public MobileElement getAvatar() {
        return avatar;
    }
    public MobileElement getMessageTextField() {
        return messageTextField;
    }
    public MobileElement getAllowAlertButton() {
        return allowAlertButton;
    }
    public MobileElement getQuickReplyButton() {
        return quickReplyButton;
    }
    public MobileElement getQuickReplyEditButton() {
        return quickReplyEditButton;
    }
    public MobileElement getPmButton() {
        return pmButton;
    }
    public MobileElement getPhotoAttachmentButton() {
        return photoAttachmentButton;
    }
    public MobileElement getCameraAttachmentButton() {
        return cameraAttachmentButton;
    }

    public MobileElement getLocationAttachmentButton() {
        return locationAttachmentButton;
    }
    public MobileElement getFileAttachmentButton() {
        return fileAttachmentButton;
    }
    public MobileElement getVoiceAttachmentButton() {
        return voiceAttachmentButton;
    }
    public MobileElement getSendButton() {
        return sendButton;
    }
    public MobileElement getLatestMessageIsPriority() {
        return latestMessageIsPriority;
    }
    public MobileElement getSentDeliveredStatus() {
        return sentDeliveredStatus;
    }
    public MobileElement getSendMessageEmptyStateButton() {
        return sendMessageEmptyStateButton;
    }
    public String getDynamicLatestmessage() {
        return DYNAMICLATESTMESSAGE;
    }
    public List<MobileElement> getLatestMessage() {
        return latestMessage;
    }
    public MobileElement getLatestVoiceMessage() {
        return latestVoiceMessage;
    }
    public MobileElement getLatestMessageStatus() {
        return latestMessageStatus;
    }
    public List<MobileElement> getLatestMessagesStatus() {
        return latestMessagesStatus;
    }

    public MobileElement getCameraRoll() {
        return cameraRoll;
    }
    public String getCameraRollMediaType() {
        return CAMERAROLLMEDIATYPE;
    }
    public MobileElement getCameraViewBackFacing() {
        return cameraViewBackFacing;
    }
    public String getCameraModeVideo() {
        return DYNAMICCAMERAMODEVIDEO;
    }
    public MobileElement getCameraRecordVideoButton() {
        return cameraRecordVideoButton;
    }
    public MobileElement getCameraStopRecordingVideoButton() {
        return cameraStopRecordingVideoButton;
    }
    public MobileElement getCameraTakePhotoButton() {
        return cameraTakePhotoButton;
    }
    public MobileElement getCameraUsePhoto() {
        return cameraUsePhoto;
    }
    public MobileElement getCameraUseVideo() {
        return cameraUseVideo;
    }
    public MobileElement getVoiceRecordStartStop() {
        return voiceRecordStartStop;
    }

    public MobileElement getVoiceRecordRecordingText() {
        return voiceRecordRecordingText;
    }
    public MobileElement getLocationInProgressIndicator() {
        return locationInProgressIndicator;
    }
    public MobileElement getLatestMessageHasImageAttachment() {
        return latestMessageHasImageAttachment;
    }
    public MobileElement getLibraryChooseVideo() {
        return libraryChooseVideo;
    }
    public MobileElement getLastMessageImage() {
        return lastMessageImage;
    }
    public MobileElement getImageObject() {
        return imageObject;
    }
    public String getConversationReceivePriority1() {
        return CONVERSATIONRECEIVEPRIORITY1;
    }
    public String getConversationReceivePriority2() {
        return CONVERSATIONRECEIVEPRIORITY2;
    }
    public String getRosterReceivePriority() {
        return ROSTERRECEIVEPRIORITY;
    }
    public String getRosterReceiveMessage() {
        return ROSTERRECEIVEMESSAGE;
    }

    public String getRosterMessage() {
        return ROSTERMESSAGE;
    }
    public MobileElement getLatestMessagePriority() {
        return latestMessagePriority;
    }
    public List<MobileElement> getDynamicQuickPickMessageValue() {
        return quickPickText;
    }
    public MobileElement getLatestMessagePriorityStatus() {
        return latestMessagePriorityStatus;
    }
    public List<MobileElement> getPhotos() { return photos; }
    public MobileElement getLatestMessageHasImagePriority() { return latestMessageHasImagePriority; }
    public MobileElement getLatestMessagePriorityHasImageStatus() { return latestMessagePriorityHasImageStatus; }
    public MobileElement getLatestLocationMessage() { return latestLocationMessage; }
    public MobileElement getVoiceRecordStop() { return voiceRecordStop; }
    public MobileElement getSendOn() { return sendOn; }

    public MobileElement getCameraMode() { return cameraMode; }
    public MobileElement getCloseMarkup() { return closeMarkup; }
    public MobileElement getLatestMessageHasImageStatus() { return latestMessageHasImageStatus; }
    public MobileElement getCameraModeVideo2() {
        return cameraModeVideo2;
    }
    public MobileElement getAllPhotosPath() {
        return allPhotosPath;
    }
    public MobileElement getBrowseSelectedTab() {
        return browseSelectedTab;
    }
    public MobileElement getiCloudLocationButton() {
        return iCloudLocationButton;
    }
    public MobileElement getDocDocument() {
        return docDocument;
    }
    public MobileElement getPdfDocument() {
        return pdfDocument;
    }

    public MobileElement getPpDocument() {
        return ppDocument;
    }
    public MobileElement getXlsDocument() {
        return xlsDocument;
    }
    public MobileElement getMessageOptionDetailsButton() {
        return messageOptionDetailsButton;
    }
    public MobileElement getMessageOptionForwardButton() {
        return messageOptionForwardButton;
    }
    public MobileElement getMessageOptionRecallButton() {
        return messageOptionRecallButton;
    }
    public MobileElement getMessageOptionResendAsPriorityButton() {
        return messageOptionResendAsPriorityButton;
    }
    public MobileElement getMessageOptionResendButton() {
        return messageOptionResendButton;
    }
    public MobileElement getMessageOptionShowMeMoreItemsButton() {
        return messageOptionShowMeMoreItemsButton;
    }
    public MobileElement getFirstQuickReplyOption() {
        return firstQuickReplyOption;
    }
    public MobileElement getSecondQuickReplyOption() {
        return secondQuickReplyOption;
    }
    public MobileElement getThirdQuickReplyOption() {
        return thirdQuickReplyOption;
    }
    public String getDynamicMessageValue(){return DYNAMICMESSAGEVALUE;}
    public List<MobileElement> getQuickPickText() {
        return quickPickText;
    }
    public List<MobileElement> getSentDeliveredStatuses() {
        return sentDeliveredStatuses;
    }
    public MobileElement getEmojiKeyboardButton() {
        return emojiKeyboardButton;
    }
    public MobileElement getRedBalloonEmojiButton() {
        return redBalloonEmojiButton;
    }

    public MobileElement getLolEmojiButton() {
        return lolEmojiButton;
    }
    public MobileElement getBlowKissEmojiButton() {
        return blowKissEmojiButton;
    }
}