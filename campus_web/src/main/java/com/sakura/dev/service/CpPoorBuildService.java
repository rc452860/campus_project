package com.sakura.dev.service;

import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStipendApplication;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpPoorBuildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yth on 2017/6/5.
 */
@Service
public class CpPoorBuildService {
    @Autowired
    CpPoorBuildRepository cpPoorBuildRepository;
    @Autowired
    CpStudentService cpStudentService;
    /**
     * 建档表插入学生
     * @param cpPoorBuild
     * @return
     */
    public Boolean insertStudent(CpPoorBuild cpPoorBuild){
        CpPoorBuild cpPoorBuild1 = cpPoorBuildRepository.save(cpPoorBuild);
        if (cpPoorBuild1!=null){
            return true;
        }
        return false;
    }

    /**
     * 通过学生身份证号查询
     * @param IdCard
     * @return
     */
    public CpPoorBuild getStudent(String IdCard){
        CpPoorBuild cpPoorBuild = cpPoorBuildRepository.findOne(IdCard);
        if (cpPoorBuild!=null){
            return cpPoorBuild;
        }
        return null;
    }

    /**
     * 复制学生基本信息并返回
     * @param arg
     * @return
     */
	public CpPoorBuild getBaseInfo(CpStudent arg) {
        CpStudent cpStudent = cpStudentService.get(arg);

        if (cpStudent!=null){
            CpPoorBuild cpPoorBuild = new CpPoorBuild();
            cpPoorBuild.setCpSno(cpStudent.getCpSno());
            cpPoorBuild.setCpAcademy(cpStudent.getCpAcademy().getCpName());
            cpPoorBuild.setCpIdCardNo(cpStudent.getCpIdCardNo());
            cpPoorBuild.setCpSex(cpStudent.getCpSex());
            cpPoorBuild.setCpClass(cpStudent.getClass().getName());
            cpPoorBuild.setCpMajor(cpStudent.getCpMajor().getCpName());
            cpPoorBuild.setCpStudyLength(cpStudent.getCpMajor().getCpLength());

            return cpPoorBuild;
        }
        return null;

    }
}
