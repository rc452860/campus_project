package com.sakura.dev.service;

import com.sakura.dev.domain.CpDocTag;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpTeacher;
import com.sakura.dev.excption.CampusExcption;
import com.sakura.dev.repository.CpDocTagRepository;
import com.sakura.dev.repository.specification.GenericSpecBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

/**
 * Created by rc452 on 2017/6/14.
 */
@Service
public class CpDocTagService {
    @Autowired
    CpDocTagRepository cpDocTagRepository;
    @Autowired
    CpPoorBuildService cpPoorBuildService;
    @Autowired
    CpTeacherService cpTeacherService;

    CpDocTag get(CpDocTag cpDocTag){
        return this.get(cpDocTag.getCpName());
    }

    CpDocTag get(String name){
        return cpDocTagRepository.findByCpName(name);
    }

    public CpDocTag open(CpDocTag cpDocTag) {
        return cpDocTagRepository.save(cpDocTag);
    }

    public boolean existOpen() {
        Date date = new Date();
        GenericSpecBuilder<CpDocTag> builder = new GenericSpecBuilder<CpDocTag>();
        builder.with("cpEnd", ">=", date);
        List<CpDocTag> list = cpDocTagRepository.findAll(builder.build());
        return list.size() > 0;
    }

    public Page<CpDocTag> getdocTags(Pageable pageable) {
        try {
            Field sort = pageable.getClass().getDeclaredField("sort");
            sort.setAccessible(true);
            sort.set(pageable,new Sort(Sort.Direction.DESC,"cpEnd"));
            return cpDocTagRepository.findAll(pageable);
        }catch (Exception e){
            throw new CampusExcption("排序设置失败");
        }
    }
    @Modifying
    @Transactional
    public void del(Long[] ids) {
        for (long i : ids){
            cpDocTagRepository.deleteByCpId(i);
        }
    }

	public CpDocTag getCurrentOpen() {
        Date date = new Date();
        GenericSpecBuilder<CpDocTag> builder = new GenericSpecBuilder<CpDocTag>();
        builder.with("cpEnd", ">=", date);
        List<CpDocTag> list = cpDocTagRepository.findAll(builder.build());
        return list.size()>0?list.get(0):null;
	}


    public Page<CpPoorBuild> getList(Pageable pageable,CpDocTag cpDocTag,CpTeacher cpTeacher) {
        return cpPoorBuildService.getList(pageable, cpDocTag, cpTeacher);
    }

    public CpDocTag getDocTagById(Long cpDocTagId) {
        /*GenericSpecBuilder<CpDocTag> builder = new GenericSpecBuilder<CpDocTag>();
        builder.with("cpId", SearchOperation.EQUALITY,cpDocTagId);*/
        return cpDocTagRepository.getOne(cpDocTagId);
    }

    public boolean existCpDcoTag(CpDocTag cpDocTag) {
        return cpDocTagRepository.getOne(cpDocTag.getCpId()) != null;
    }
}
