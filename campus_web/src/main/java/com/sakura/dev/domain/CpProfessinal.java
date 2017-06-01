package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 专业
 */
@Data
@Entity
public class CpProfessinal {
    @Id
    @GeneratedValue
    private long cpId;
    @Column(unique = true)
    private String cpName;
    private String cpDegree;    //层次
    private int cpLengthOfSchool;    //学制
    @ManyToMany
    private Set<CpTeacher> cpTeachers;
    @OneToMany
    private Set<CpClass> cpClasses;
}
