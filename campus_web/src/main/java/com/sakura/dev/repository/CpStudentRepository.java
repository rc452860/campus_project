package com.sakura.dev.repository;

import com.sakura.dev.domain.CpStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by rc452 on 2017/5/22.
 */
public interface CpStudentRepository extends JpaRepository<CpStudent,String> {
    CpStudent findByCpIdCardNoOrCpSno(String idCard,String CpSno);
}
