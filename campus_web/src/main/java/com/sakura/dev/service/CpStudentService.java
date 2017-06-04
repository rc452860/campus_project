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

    public CpStudent get(CpStudent arg){
        return cpStudentRepository.findByCpIdCardNoOrCpSno(arg.getCpIdCardNo(),arg.getCpSno());
    }

    public CpStudent get(String arg){
        return cpStudentRepository.findByCpIdCardNoOrCpSno(arg,arg);
    }

    /**
     * 根据CpStudent对象来重置密码
     * @param arg
     * @return
     */
    public CpStudent resetPassword(CpStudent arg){
        CpStudent cpStudent = this.get(arg);
        cpStudent.setPassword(null);
        return cpStudentRepository.save(cpStudent);
    }

    /**
     * 根据用户名来重置密码
     * @param username
     * @return
     */
    public CpStudent restPassowrd(String username) {
        return resetPassword(this.get(username));
    }
}
