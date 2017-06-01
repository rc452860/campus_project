package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
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
    @Id
    @GeneratedValue
    private Long cpId;
    @Column(unique = true)
    private String cpName;
    @ManyToMany
    private Set<CpTeacher> cpTeachers;
    @OneToMany
    private Set<CpFaculty> cpFaculties;
}
