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


}
