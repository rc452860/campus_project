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
     * 学生申请建档
     * @param cpPoorBuild
     * @return
     */
    public Boolean StipendApplication(CpPoorBuild cpPoorBuild){
        CpPoorBuild cpPoorBuild1 = cpPoorBuildRepository.save(cpPoorBuild);
        if (cpPoorBuild1!=null){
            return true;
        }
        return false;
    }
}
