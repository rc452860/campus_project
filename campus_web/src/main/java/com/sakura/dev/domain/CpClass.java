package com.sakura.dev.domain;

import lombok.Data;
import sun.rmi.transport.tcp.TCPTransport;

import javax.persistence.*;
import java.util.*;

/**
 * Created by rc452 on 2017/6/1.
 */
@Data
@Entity
public class CpClass {
    @Id
    @GeneratedValue
    private Long cpId;
    @Column(unique = true)
    private String cpName;
    private String cpGrade;    //年级
    @ManyToMany
    private Set<CpTeacher> cpTeachers;
    @ManyToOne
    private CpProfessinal cpProfessinal;
}
