package com.seproject.web.parameter;

public class MessageParameter {
    private String keyID;
    private String senderID;
    private String receiverID;
    private int type;//消息类型：0 常规消息
    private String content;//消息内容，有时有用

    public String getKeyID() {
        return keyID;
    }

    public String getSenderID() {
        return senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public int getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
