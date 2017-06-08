package com.sakura.dev.controller.dto;


/**
 * Created by yth on 2017/6/4.
 */
public class UserAccount extends Result{
    private String password;
    private String new_password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
