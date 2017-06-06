package com.sakura.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sakura.dev.repository.CpStudentRepository;
import lombok.Data;
import org.hibernate.annotations.*;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;

/**
 * Created by rc452 on 2017/5/21.
 */
@Entity
@Data
public class CpStudent {
    @Id
    @Column(nullable = true,unique = true)
    private String cpIdCardNo;    // 身份证号码
    private String cpSno;   // 学号
    private String cpName;   // 姓名
    private String cpOldName;   // 曾用名
    private String cpSex;    // 性别
    @JsonIgnore
    private String password;     //密码

    @OneToOne
    @JoinColumn(name = "cp_academy",foreignKey = @ForeignKey(name = "none"))
    private CpAcademy cpAcademy;

    @OneToOne
    @JoinColumn(name = "cp_major",foreignKey = @ForeignKey(name = "none"))
    private CpAcademy cpMajor;

    @OneToOne
    @JoinColumn(name = "cp_class",foreignKey = @ForeignKey(name = "none"))
    private CpAcademy cpClass;

}
