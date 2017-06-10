package com.sakura.dev.repository;

import com.sakura.dev.domain.CpStipendApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yth on 2017/6/5.
 */
public interface CpStipendApplicationRepository extends JpaRepository<CpStipendApplication,String>,JpaSpecificationExecutor {
}
