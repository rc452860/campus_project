package com.sakura.dev.repository;

import com.sakura.dev.domain.CpFile;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Created by rc452 on 2017/6/22.
 */
public interface CpFileRepository extends JpaRepository<CpFile,Long>,JpaSpecificationExecutor {
	CpFile findByCpId(Long id);

	void deleteByCpId(Long id);
}
