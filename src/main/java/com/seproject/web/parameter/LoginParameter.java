package com.seproject.web.parameter;

/**
 * 在LoginController/changePassword中使用
 */
public class LoginParameter {
    private String uid;
    private String password;

    public String getUid() {
        return uid;
    }

    public String getPassword() {
        return password;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
