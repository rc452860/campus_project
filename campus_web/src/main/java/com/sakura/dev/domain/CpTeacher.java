package com.sakura.dev.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 教师
 */
@Data
@Entity
public class CpTeacher {
    public final static String XGB = "学工部";
    public final static String FDY = "辅导员";
    @Id
    @GeneratedValue
    private long cpId;    //主键
    private String cpName;    //姓名
    private String cpTitle;   //职称
    private String cpTno;    //工号
    @JsonIgnore
    private String cpPassword;    //密码
    private String cpRole;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cp_academy_cp_teacher", joinColumns = @JoinColumn(name = "cp_academy_cp_id"),
            inverseJoinColumns = @JoinColumn(name = "cp_teacher_cp_id"), foreignKey = @ForeignKey(name = "none"), inverseForeignKey = @ForeignKey(name = "none"))
//    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
//    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<CpAcademy> cpAcademies;
}
