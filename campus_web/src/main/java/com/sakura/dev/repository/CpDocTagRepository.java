package com.sakura.dev.repository;

import com.sakura.dev.domain.CpDocTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by rc452 on 2017/6/14.
 */
public interface CpDocTagRepository extends JpaRepository<CpDocTag,Long>,JpaSpecificationExecutor<CpDocTag> {
    CpDocTag findByCpName(String name);
}
