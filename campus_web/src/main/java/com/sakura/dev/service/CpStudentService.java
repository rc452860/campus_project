package com.sakura.dev.service;

import com.sakura.dev.controller.dto.UserAccount;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import org.dom4j.tree.BackedList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * Created by rc452 on 2017/5/22.
 */
@Service
public class CpStudentService{
    @Autowired
    CpStudentRepository cpStudentRepository;
    private Logger logger = LoggerFactory.getLogger(CpStudentService.class);
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


    /**
     * 修改密码
     * @param cpStudent
     * @param account
     * @return
     */
    public Boolean updatePassword(CpStudent cpStudent, UserAccount account){
        if (StringUtils.isEmpty(cpStudent.getPassword())){
            if (account.getPassword().equals(cpStudent.getCpIdCardNo())||account.getPassword().equals(cpStudent.getCpSno())){
                cpStudent.setPassword(account.getNew_password());
                cpStudentRepository.save(cpStudent);
                return true;
            }
        }else {
            if (cpStudent.getPassword().equals(account.getPassword())){
                cpStudent.setPassword(account.getNew_password());
                cpStudentRepository.save(cpStudent);
                return true;
            }
        }
        return false;
    }
}
