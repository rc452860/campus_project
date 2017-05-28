package com.sakura.dev.service.impl;

import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import com.sakura.dev.service.CpStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by rc452 on 2017/5/22.
 */
@Service
public class CpStudentServiceImpl implements CpStudentService {
    @Autowired
    CpStudentRepository cpStudentRepository;

    public void save(CpStudent cpStudent) {
        cpStudentRepository.save(cpStudent);
    }
}
