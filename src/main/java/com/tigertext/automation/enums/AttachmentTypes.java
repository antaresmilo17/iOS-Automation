package com.tigertext.automation.enums;

public enum AttachmentTypes {

    PDF("pdf"),
    DOC("doc"),
    PPT("ppt"),
    IMAGELIBRARY("image library"),
    IMAGECAMERA("image camera"),
    VIDEOLIBRARY("video library"),
    VIDEOCAMERA("video camera"),
    VOICE("voice"),
    LOCATION("location"),
    XCEL("xcel");


    private String attachmentTypes;

    AttachmentTypes(String attachmentTypes) {
        this.attachmentTypes = attachmentTypes;
    }

    public String getAttachmentTypes() {
        return attachmentTypes;
    }

    @Override
    public String toString() { return this.attachmentTypes;}
}
