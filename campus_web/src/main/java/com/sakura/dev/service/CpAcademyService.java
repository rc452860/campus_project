package com.sakura.dev.service;

import com.sakura.dev.domain.CpAcademy;
import com.sakura.dev.repository.CpAcademyRepository;
import com.sakura.dev.repository.specification.GenericSpec;
import com.sakura.dev.repository.specification.GenericSpecBuilder;
import com.sakura.dev.repository.specification.SearchCriteria;
import com.sakura.dev.repository.specification.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rc452 on 2017/6/3.
 */
@Service
public class CpAcademyService {
    @Autowired
    private CpAcademyRepository cpAcademyRepository;

    @Transactional
    public CpAcademy getOrCreate(CpAcademy arg){
        CpAcademy cpAcademy = cpAcademyRepository.findByCpName(arg.getCpName());
        return cpAcademy == null ? cpAcademyRepository.save(arg) : cpAcademy;
    }

	public List<CpAcademy> getAcademyByName(String name, int rank) {
		GenericSpecBuilder<CpAcademy> builder = new GenericSpecBuilder<CpAcademy>();
		builder.with("cpName","~",String.format("%%%s%%",name));
		builder.with("cpRank", ":", rank);
		return cpAcademyRepository.findAll(builder.build());
	}

	public CpAcademy getParentById(Long id) {
		GenericSpecBuilder<CpAcademy> builder = new GenericSpecBuilder<CpAcademy>();
		builder.with("cpParent",":","id");
		return cpAcademyRepository.findOne(builder.build());
	}
}
