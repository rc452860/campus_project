package com.sakura.dev.domain;

import com.sakura.dev.repository.CpStudentRepository;
import lombok.Data;
import org.springframework.context.annotation.Primary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by rc452 on 2017/5/21.
 */
@Entity
@Data
public class CpStudent {
    @Id
    @Column(nullable = false)
    private String cpIdCardNo;    // 身份证号码
    private String cpSno;   // 学号
    private String cpName;   // 姓名
    private String cpOldName;   // 曾用名
    private String cpSex;    // 性别
    @OneToOne
    private CpAcademy cpAcademy;    // 学院
    @OneToOne
    private CpFaculty cpFaculty;    // 院系
    @OneToOne
    private CpStudentRepository cpProfessionalName;    //专业名称
    @OneToOne
    private CpClass cpClass;    // 班级
    private String password;     //密码
}
