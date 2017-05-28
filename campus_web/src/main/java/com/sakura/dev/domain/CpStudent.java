package com.sakura.dev.domain;

import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rc452 on 2017/5/21.
 */
@Entity
@Data
public class CpStudent {
    private String cpSno;   // 学号
    private String cpName;   // 姓名
    private String cpOldName;   // 曾用名
    private String cpSex;    // 性别
    private String cpAcademy;    // 学院
    private String cpFaculty;    // 院系
    @Id
    @Column(nullable = false)
    private String cpIdCardNo;    // 身份证号码
    private String cpProfessionalName;    //专业名称
    private String cpClass;    // 班级
    private String cpGrade;    //年级
    private String cpDegree;    //层次
    private int cpLengthOfSchool;    //学制
    private String password;    //密码
}
