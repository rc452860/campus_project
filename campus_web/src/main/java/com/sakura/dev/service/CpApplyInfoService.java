package com.sakura.dev.service;

import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.domain.CpApplyInfo;
import com.sakura.dev.repository.CpApplyInfoRepository;
import com.sakura.dev.repository.specification.GenericSpecBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by rc452 on 2017/6/14.
 */
@Service
public class CpApplyInfoService {
    @Autowired
    CpApplyInfoRepository cpApplyInfoRepository;

    CpApplyInfo get(CpApplyInfo cpApplyInfo){
        return this.get(cpApplyInfo.getCpName());
    }

    CpApplyInfo get(String name){
        return cpApplyInfoRepository.findByCpName(name);
    }

    public CpApplyInfo open(CpApplyInfo cpApplyInfo) {
        return cpApplyInfoRepository.save(cpApplyInfo);
    }

    public boolean existOpen() {
        Date date = new Date();
        GenericSpecBuilder<CpApplyInfo> builder = new GenericSpecBuilder<CpApplyInfo>();
        builder.with("cpEnd", ">", date);
        List<CpApplyInfo> list = cpApplyInfoRepository.findAll(builder.build());
        return list.size() > 0;
    }
}
