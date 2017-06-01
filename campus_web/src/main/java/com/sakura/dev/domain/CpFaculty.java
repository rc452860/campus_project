package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * Created by rc452 on 2017/6/1.
 */

/**
 * 系别
 */
@Data
@Entity
public class CpFaculty {
    @Id
    @GeneratedValue
    private long cpId;
    @Column(unique = true)
    private String cpName;
    @ManyToMany
    private Set<CpTeacher> cpTeachers;
    @OneToMany
    private Set<CpProfessinal> cpProfessinals;
    @ManyToOne
    private CpAcademy cpAcademy;
}
