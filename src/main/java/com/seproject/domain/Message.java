package com.seproject.domain;

import com.seproject.common.Key;
import com.seproject.common.Searchable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="message")
public class Message {
    //消息
    @Key
    @Id
    @Column(name="keyid")
    String keyID="";//主键：用户名+”*“+系统时间
    @Column(name="senderid")
    @Searchable(varName = "senderID")
    String senderID="";
    @Column(name="receiverid")
    @Searchable(varName = "receiverID")
    String receiverID="";
    @Column(name="type")
    int type;
    @Column(name="content")
    String content="";

    public Message(String keyID,String senderID,String receiverID,int type,String content){
        setKeyID(keyID);
        setSenderID(senderID);
        setReceiverID(receiverID);
        setType(type);
        setContent(content);
    }
    public String getKeyID() {
        return keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getSenderID() {
        return senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(String receiverID) {
        this.receiverID = receiverID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public  Message(){

    }
}
