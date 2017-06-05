package com.sakura.dev.service;

import com.sakura.dev.domain.CpStipendApplication;
import com.sakura.dev.repository.CpStipendApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yth on 2017/6/5.
 */
@Service
public class CpStipendApplicationService {
    @Autowired
    CpStipendApplicationRepository cpStipendApplicationRepository;

    /**
     * 学生申请建档
     * @param cpStipendApplication
     * @return
     */
    public Boolean StipendApplication(CpStipendApplication cpStipendApplication){
        CpStipendApplication cpStipendApplication1 = cpStipendApplicationRepository.save(cpStipendApplication);
        if (cpStipendApplication1!=null){
            return true;
        }
        return false;
    }
}
