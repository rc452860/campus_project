package com.sakura.dev.repository;

import com.sakura.dev.domain.CpTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by rc452 on 2017/6/2.
 */
public interface CpTeacherRepository extends JpaRepository<CpTeacher,Long>,JpaSpecificationExecutor {
    CpTeacher findByCpTno(String usernmae);
}
