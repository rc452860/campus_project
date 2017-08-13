package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 学院
 */
@Data
@Entity
public class CpAcademy {
    public final static int RANK_ACADEMY = 1;//学院
    public final static int RANK_MAJOR = 2;//专业
    public final static int RANK_CLASS = 3;//班级
    @Id
    @GeneratedValue
    private Long cpId;
    @Column(unique = true)
    private String cpName;
    private int cpRank;
    private String cpDegree;
    private int cpLength;
    @OneToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
    @org.hibernate.annotations.ForeignKey(name = "none")
    private CpAcademy cpParent;
    private String cpLevel;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "cp_academy_cp_teacher", joinColumns = @JoinColumn(name = "cp_teacher_cp_id"),
            inverseJoinColumns = @JoinColumn(name = "cp_academy_cp_id"), foreignKey = @ForeignKey(name = "none"), inverseForeignKey = @ForeignKey(name = "none"))
//    @JoinColumn(foreignKey = @ForeignKey(name = "none"))
//    @org.hibernate.annotations.ForeignKey(name = "none")
    private Set<CpTeacher> cpTeacher;

    public CpAcademy(){}

    /**
     * Construction
     * @param cpName 姓名
     * @param cpRank 等级1.学院 2.专业 3.班级
     * @param cpDegree 学位 if rank == 1 this field should be null
     * @param cpLength 学制
     * @param cpParent 上级
     */
    public CpAcademy(String cpName, int cpRank, String cpDegree, int cpLength,CpAcademy cpParent) {
        this(cpName,cpRank,cpDegree,cpLength,cpParent,null,null);
    }

    public CpAcademy(String cpName, int cpRank, String cpDegree, int cpLength, CpAcademy cpParent, String cpLevel){
        this(cpName,cpRank,cpDegree,cpLength,cpParent,cpLevel,null);
    }

    /**
     *
     * @param cpName 姓名
     * @param cpRank 等级1.学院 2.专业 3.班级
     * @param cpDegree 学位 if rank == 1 this field should be null
     * @param cpLength 学制
     * @param cpParent 上级
     * @param cpLevel 年级
     * @param cpTeacher 权限
     */
    public CpAcademy(String cpName, int cpRank, String cpDegree, int cpLength, CpAcademy cpParent, String cpLevel, Set<CpTeacher> cpTeacher) {
        this.cpName = cpName;
        this.cpRank = cpRank;
        this.cpDegree = cpDegree;
        this.cpLength = cpLength;
        this.cpParent = cpParent;
        this.cpLevel = cpLevel;
        this.cpTeacher = cpTeacher;
    }


}
