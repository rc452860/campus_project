package com.sakura.dev.repository;

import com.sakura.dev.domain.CpAcademy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by rc452 on 2017/6/2.
 */
public interface CpAcademyRepository extends JpaRepository<CpAcademy,String>  {
    CpAcademy findByCpName(String cpName);
}
