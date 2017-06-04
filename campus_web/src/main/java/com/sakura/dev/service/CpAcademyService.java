package com.sakura.dev.service;

import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.repository.CpAcademyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rc452 on 2017/6/3.
 */
@Service
public class CpAcademyService {
    @Autowired
    private CpAcademyRepository cpAcademyRepository;

    @Transactional
    public CpAcademy getOrCreate(CpAcademy arg){
        CpAcademy cpAcademy = cpAcademyRepository.findByCpName(arg.getCpName());
        return cpAcademy == null ? cpAcademyRepository.save(arg) : cpAcademy;
    }
}
