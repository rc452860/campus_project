package com.sakura.dev.repository;

import com.sakura.dev.domain.CpPoorBuild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by rc452 on 2017/6/2.
 */
public interface CpPoorBuildRepository extends JpaRepository<CpPoorBuild,String>,JpaSpecificationExecutor {
}
