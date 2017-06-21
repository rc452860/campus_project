package com.sakura.dev.domain;

import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.util.Date;

/**
 * 贫困生简单
 * Created by yth on 2017/5/27.
 */
@Entity
@Data
public class CpPoorBuild {
    private String cpSno;    //学号
    private String cpAcademy;  //学院
    private String cpName;  //姓名
    private String cpSex;  //性别
    private int cpStudyLength;  //学制
    private String cpGrade;  //年级
    private String cpMajor;
    private String cpClass;  //班级
    private String cpIdCardNo;   //身份证号
    private Date cpWriteTime;  //填表时间
    private Date cpBirthday;  //出身年月
    private String cpNationality;  //名族
    private String cpImg;  //头像

    private String cpPolitical;  //政治面貌
    private String cpRoom;  //寝室
    private String cpTel;  //联系方式
    private String cpInsurance;  //是否购买保险
    @Id
    private String cpBankCard;  //银行卡号
    private String cpAddress;  //家庭住址
    private String cpPostcode;  //邮政编码
    private String cpFinancialResources;   //经济来源
    private Integer cpFamilyIncome;  //家庭收入
    private String cpLoan;   //是否生源地贷款
    private Integer cpPopulation;  //人口数量
    private String cpFatherName;  //父亲名字
    private String cpFWorkAddress;   //父亲工作单位
    private String cpFatherIncome;   //父亲收入
    private String cpMotherName;  //母亲名字
    private String cpMWorkAddress;   //母亲工作单位
    private String cpMotherIncome;   //母亲收入
    private String cpOthers1Name; //其他成员1姓名
    private String cpOthers1WorkAddress;    //其他成员1工作单位
    private String cpOthers1Income;    //其他成员1收入
    private String cpOthers2Name; //其他成员2姓名
    private String cpOthers2WorkAddress;    //其他成员2工作单位
    private String cpOthers2Income;    //其他成员2收入
    private String cpOthers3Name; //其他成员3姓名
    private String cpOthers3WorkAddress;    //其他成员3工作单位
    private String cpOthers3Income;    //其他成员3收入
    private String cpApplications;    //个人申请材料
    private String cpSpecialPoor;    //特困证明材料
    private String cpLaidOff;    //下岗证明材料
    private String cpDisability;   //残疾证材料
    private String cpLowSecurity;   //低保证材料
    private String cpCondition;    //病情证明材料
    private String cpAwards;   //获奖证明材料
    private Integer cpCounselorResult = 0;   //辅导员审核结果：0表示不通过，1表示通过
    private String cpCounselorRemarks;  //辅导员审核备注
    private Integer cpSuperResult = 0;  //学工部审核结果：0表示不通过，1表示通过
    private String cpSuperRemarks;  //学工部审核备注
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    public CpDocTag cpDocTag;

}
