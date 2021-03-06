package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * 助学金申请表字段
 * Created by yth on 2017/6/5.
 */
@Entity
@Data
public class CpStipendApplication {
    private String cpSchool;   //学校
    private String cpDepartment;  //院系
    private String cpClass;  //班级
    private String cpSno;  //学号
    private String cpName;  //姓名
    private String cpSex;  //性别
    private Date cpBirthday;  //出身年月
    private String cpImg;  //头像
    private String cpNationality;  //名族
    private String cpPolitical;  //政治面貌
    private Date cpAdmissionTime;  //入学时间
    @Id
    private String cpCard;   //身份证号
    private String cpTel;  //联系方式
    private String cpQQ;  //QQ号
    private String cpFamilyAccount;  //家庭户口
    private Integer cpPopulation;  //人口数量
    private Integer cpFamilyIncome;  //家庭收入
    private String cpCapitaIncome;  //人均月收入
    private String cpFinancialResources;   //经济来源
    private String cpPoorGrade;  //贫困等级
    private String cpFamilyAddress;  //家庭地址
    private String cpParentsTel;  //家长联系方式
    private String cpSubsidizeMoney;  //上学年受助金额
    private String cpSubsidizeUse;  //上学年资助用途
    private String cpThisSubsidizeUse;  //本学年资助用途
    private String cpOpenBank;  //开户银行
    private String cpBankCard;  //银行卡号
    private String cpOthers1Name; //其他成员1姓名
    private Integer cpOthers1Age;  //其他成员1年龄
    private String cpOthers1relation;  //其他成员1与本人关系
    private String cpOthers1WorkAddress;    //其他成员1工作单位
    private String cpOthers2Name; //其他成员2姓名
    private Integer cpOthers2Age;  //其他成员2年龄
    private String cpOthers2Relation;  //其他成员2与本人关系
    private String cpOthers2WorkAddress;    //其他成员2工作单位
    private String cpOthers3Name; //其他成员3姓名
    private Integer cpOthers3Age;  //其他成员3年龄
    private String cpOthers3Relation;  //其他成员3与本人关系
    private String cpOthers3WorkAddress;    //其他成员3工作单位
    private String cpApplicationReason;  //申请理由
    @Column(name="cpCounselorResult",columnDefinition="tinyint default 0")
    private Integer cpCounselorResult;   //辅导员审核结果：0表示不通过，1表示通过
    private String cpCounselorRemarks;  //辅导员审核备注
    @Column(name="cpSuperResult",columnDefinition="tinyint default 0")
    private Integer cpSuperResult;  //学工部审核结果：0表示不通过，1表示通过
    private String cpSuperRemarks;  //学工部审核备注
}
