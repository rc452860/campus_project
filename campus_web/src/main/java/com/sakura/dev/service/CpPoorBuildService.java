package com.sakura.dev.service;

import com.sakura.dev.domain.CpDocTag;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpDocRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sakura.dev.service.CpDocTagService;

/**
 * Created by yth on 2017/6/5.
 */
@Service
public class CpPoorBuildService {
    @Autowired
    CpDocRepository cpDocRepository;
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    CpDocTagService cpDocTagService;

    /**
     * 建档表插入学生
     * @param cpPoorBuild
     * @return
     */
    public Boolean insertStudent(CpPoorBuild cpPoorBuild){
        CpDocTag cpDocTag =cpDocTagService.getCurrentOpen();
        if(cpDocTag!=null){
            cpPoorBuild.setCpDocTag(cpDocTag);
            CpPoorBuild cpPoorBuild1 = cpDocRepository.save(cpPoorBuild);
            if (cpPoorBuild1!=null){
                return true;
            }
        }
        return false;
    }

    /**
     * 通过学生身份证号查询
     * @param IdCard
     * @return
     */
    public CpPoorBuild getStudent(String IdCard){
        CpPoorBuild cpPoorBuild = cpDocRepository.findOne(IdCard);
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
            cpPoorBuild.setCpName(cpStudent.getCpName());
            cpPoorBuild.setCpGrade(cpStudent.getCpClass().getCpLevel());
            cpPoorBuild.setCpAcademy(cpStudent.getCpAcademy().getCpName());
            cpPoorBuild.setCpIdCardNo(cpStudent.getCpIdCardNo());
            cpPoorBuild.setCpSex(cpStudent.getCpSex());
            cpPoorBuild.setCpClass(cpStudent.getCpClass().getCpName());
            cpPoorBuild.setCpMajor(cpStudent.getCpMajor().getCpName());
            cpPoorBuild.setCpStudyLength(cpStudent.getCpMajor().getCpLength());

            return cpPoorBuild;
        }
        return null;

    }
}
