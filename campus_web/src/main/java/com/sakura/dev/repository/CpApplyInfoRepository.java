package com.sakura.dev.repository;

import com.sakura.dev.domain.CpApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by rc452 on 2017/6/14.
 */
public interface CpApplyInfoRepository extends JpaRepository<CpApplyInfo,Long>,JpaSpecificationExecutor<CpApplyInfo> {
    CpApplyInfo findByCpName(String name);
}
