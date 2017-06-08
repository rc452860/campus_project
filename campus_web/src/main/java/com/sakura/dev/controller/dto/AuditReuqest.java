package com.sakura.dev.controller.dto;

/**
 * Created by yth on 2017/6/7.
 */
public class AuditReuqest {
    private String cpCard;   //审核学生
    private String Identity;  //身份
    private Integer Result;  //审核结果

    public String getCpCard() {
        return cpCard;
    }

    public void setCpCard(String cpCard) {
        this.cpCard = cpCard;
    }

    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
    }

    public Integer getResult() {
        return Result;
    }

    public void setResult(Integer result) {
        Result = result;
    }
}
