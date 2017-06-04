package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 教师
 */
@Data
@Entity
public class CpTeacher {
    @Id
    @GeneratedValue
    private long cpId;    //主键
    private String cpName;    //姓名
    private String cpTitle;   //职称
    private String cpTno;    //工号
    private String cpPassword;    //密码
    @ManyToMany
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<CpAcademy> cpAcademies;
}
