package com.sakura.dev.service;

import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStipendApplication;
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
}
