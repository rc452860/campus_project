package com.sakura.dev.repository;

import com.sakura.dev.domain.CpAcademy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by rc452 on 2017/6/2.
 */
public interface CpAcademyRepository extends JpaRepository<CpAcademy,String> ,JpaSpecificationExecutor<CpAcademy> {
    CpAcademy findByCpName(String cpName);
}
