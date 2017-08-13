package com.sakura.dev.service;

import com.sakura.dev.context.SessionContext;
import com.sakura.dev.controller.dto.LoginRequest;
import com.sakura.dev.domain.CpTeacher;
import com.sakura.dev.excption.CampusExcption;
import com.sakura.dev.repository.CpTeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by rc452 on 2017/6/14.
 */
@Service
public class CpTeacherService {
    @Autowired
    CpTeacherRepository cpTeacherRepository;

    @Autowired
    HttpSession session;

    public CpTeacher login(LoginRequest loginRequest) {
        CpTeacher cpTeacher = this.get(loginRequest.getUsername());
        if (cpTeacher != null && cpTeacher.getCpPassword() == null) {
            return loginRequest.getUsername().equals(loginRequest.getPassword()) ? cpTeacher : null;
        } else {
            return loginRequest.getPassword().equals(loginRequest.getPassword()) ? cpTeacher : null;
        }
    }

    public CpTeacher get(String tno) {
        return cpTeacherRepository.findByCpTno(tno);
    }


    public CpTeacher getTeacherBySession() {
//        CpTeacher cpTeacher = (CpTeacher)session.getAttribute(SessionContext.TEACHER);
//        return get(cpTeacher.getCpTno());
        CpTeacher cpTeacher = (CpTeacher) session.getAttribute(SessionContext.TEACHER);
        if (cpTeacher == null) {
            throw new CampusExcption("用户未登录");
        } else {
            return cpTeacher;
        }
    }
}
