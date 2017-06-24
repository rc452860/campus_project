package com.sakura.dev.service;

import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.controller.dto.UserAccount;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpStudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import java.util.*;

/**
 * Created by rc452 on 2017/5/22.
 */
@Service
@CacheConfig(cacheNames = "students")
public class CpStudentService{
    @Autowired
    CpStudentRepository cpStudentRepository;
    private Logger logger = LoggerFactory.getLogger(CpStudentService.class);
    public CpStudent save(CpStudent cpStudent) {
        return cpStudentRepository.save(cpStudent);
    }
    @Cacheable
    @Transactional
    public CpStudent getOrCreate(CpStudent arg){
        CpStudent cpStudent = cpStudentRepository.findByCpIdCardNoOrCpSno(arg.getCpIdCardNo(),arg.getCpSno());
        return cpStudent == null?cpStudentRepository.save(arg):cpStudent;
    }
    @Cacheable(key = "#arg",condition="#arg!=null")
    public CpStudent get(CpStudent arg){
        if (arg == null||arg.getCpIdCardNo() == null||arg.getCpSno() == null){
            return null;
        }
        return cpStudentRepository.findByCpIdCardNoOrCpSno(arg.getCpIdCardNo(),arg.getCpSno());
    }
    @Cacheable(key = "#arg",condition="#arg!=null")
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
        cpStudent.setCpPassword(null);
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
        if (StringUtils.isEmpty(cpStudent.getCpPassword())){
            if (account.getPassword().equals(cpStudent.getCpIdCardNo())||account.getPassword().equals(cpStudent.getCpSno())){
                cpStudent.setCpPassword(account.getNew_password());
                cpStudentRepository.save(cpStudent);
                return true;
            }
        }else {
            if (cpStudent.getCpPassword().equals(account.getPassword())){
                cpStudent.setCpPassword(account.getNew_password());
                cpStudentRepository.save(cpStudent);
                return true;
            }
        }
        return false;
    }

    public CpStudent login(LoginRequest loginRequest) {
        CpStudent cpStudent = this.get(loginRequest.getUsername());
        if(cpStudent!=null&&cpStudent.getCpPassword()==null){
            return loginRequest.getUsername().equals(loginRequest.getPassword())?cpStudent:null;
        }else{
            return loginRequest.getPassword().equals(loginRequest.getPassword())?cpStudent:null;
        }
    }
    @Cacheable
    public Page<CpStudent> findAll(Pageable pageable){
        return cpStudentRepository.findAll(pageable);
    }
}
