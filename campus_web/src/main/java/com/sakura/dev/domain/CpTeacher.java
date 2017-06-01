package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.*;

/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 教师
 */
@Data
public class CpTeacher {
    private long cpId;    //主键
    private String cpName;    //姓名
    private String cpTitle;   //职称
    private String cpTno;    //工号
    private String cpPassword;    //密码
    @ManyToMany
    private Set<CpAcademy> cpAcademys;
    @ManyToMany
    private Set<CpFaculty> cpFacultys;
    @ManyToMany
    private Set<CpProfessinal> cpProfessinals;
    @ManyToMany
    private Set<CpClass> cpClasses;
}
