package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 国家励志助学金申请
 * Created by yth on 2017/6/5.
 */
@Entity
@Data
public class CpInspirationalStipend {
    private String cpSchool;   //学校
    private String cpDepartment;  //院系
    private String cpClass;  //班级
    private String cpSno;  //学号
    private String cpName;  //姓名
    private String cpSex;  //性别
    private String cpImg;  //头像
    private Date cpBirthday;  //出身年月
    private Date cpAdmissionTime;  //入学时间
    private String cpPolitical;  //政治面貌
    private String cpNationality;  //名族
    private String cpProfession;  //专业
    private Integer cpStudyLength;  //学制
    @Id
    private String cpCard;   //身份证号
    private String cpTel;  //联系方式
    private String cpQQ;  //QQ号
    private Integer cpRanking;  //名次
    private Integer cpTotalPeople;  //总人数
    private String cpPoorGrade;  //贫困等级
    private String cpSubsidizeMoney;  //上学年受助金额
    private String cpSubsidizeUse;  //上学年资助用途
    private String cpThisSubsidizeUse;  //本学年资助用途
    private String cpOpenBank;  //开户银行
    private String cpBankCard;  //银行卡号
    private String cpFamilyAccount;  //家庭户口
    private Integer cpPopulation;  //人口数量
    private Integer cpFamilyIncome;  //家庭收入
    private String cpCapitaIncome;  //人均月收入
    private String cpFinancialResources;   //经济来源
    private String cpFamilyAddress;  //家庭地址
    private String cpParentsTel;  //家长联系方式
    private String cpApplicationReason;  //申请理由

}
