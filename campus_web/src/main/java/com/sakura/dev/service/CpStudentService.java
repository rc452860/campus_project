package com.sakura.dev.service;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rc452 on 2017/5/22.
 */
@Service
public class CpStudentService{
    @Autowired
    CpStudentRepository cpStudentRepository;

    public CpStudent save(CpStudent cpStudent) {
        return cpStudentRepository.save(cpStudent);
    }
    @Transactional
    public CpStudent getOrCreate(CpStudent arg){
        CpStudent cpStudent = cpStudentRepository.findByCpIdCardNoOrCpSno(arg.getCpIdCardNo(),arg.getCpSno());
        return cpStudent == null?cpStudentRepository.save(arg):cpStudent;
    }
}
