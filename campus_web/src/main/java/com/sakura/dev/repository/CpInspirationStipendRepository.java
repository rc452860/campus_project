package com.sakura.dev.repository;

import com.sakura.dev.domain.CpInspirationalStipend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by yth on 2017/6/5.
 */
public interface CpInspirationStipendRepository extends JpaRepository<CpInspirationalStipend,String>,JpaSpecificationExecutor {
}
