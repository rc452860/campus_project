package com.sakura.dev.service;

import com.sakura.dev.domain.CpDocTag;
import com.sakura.dev.domain.CpPoorBuild;
import com.sakura.dev.domain.CpStudent;
import com.sakura.dev.repository.CpDocRepository;
import com.sakura.dev.repository.specification.GenericSpecBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by yth on 2017/6/5.
 */
@Service
public class CpPoorBuildService {
    @Autowired
    CpDocRepository cpDocRepository;
    @Autowired
    CpStudentService cpStudentService;
    @Autowired
    CpDocTagService cpDocTagService;

    /**
     * 建档表插入学生
     * @param cpPoorBuild
     * @return
     */
    public Boolean insertStudent(CpPoorBuild cpPoorBuild){
        if(this.hasCurrentApply(cpPoorBuild.getCpIdCardNo())){
            //当前存在已有申请
            return false;
        }
        CpDocTag cpDocTag =cpDocTagService.getCurrentOpen();
        if(cpDocTag!=null){
            cpPoorBuild.setCpDocTag(cpDocTag);
            CpPoorBuild cpPoorBuild1 = cpDocRepository.save(cpPoorBuild);
            if (cpPoorBuild1!=null){
                return true;
            }
        }
        return false;
    }

    public Boolean hasCurrentApply(String cpIdCardNo){
        CpDocTag cpDocTag =cpDocTagService.getCurrentOpen();
        GenericSpecBuilder<CpPoorBuild> build = new GenericSpecBuilder<CpPoorBuild>();
        build.with("cpDocTag",":",cpDocTag);
        build.with("cpIdCardNo",":",cpIdCardNo);
        return cpDocRepository.count(build.build())>0;
    }

    public CpPoorBuild getCurrentApply(String cpIdCardNo){
        CpDocTag cpDocTag =cpDocTagService.getCurrentOpen();
        GenericSpecBuilder<CpPoorBuild> build = new GenericSpecBuilder<CpPoorBuild>();
        build.with("cpDocTag",":",cpDocTag);
        build.with("cpIdCardNo",":",cpIdCardNo);
        List<CpPoorBuild> cpPoorBuildList = cpDocRepository.findAll(build.build());
        return cpPoorBuildList == null ||
                cpPoorBuildList.size() == 0?null:cpPoorBuildList.get(0);
    }


    /**
     * 通过学生身份证号查询
     * @param IdCard
     * @return
     */
    public CpPoorBuild getStudent(String IdCard){
        CpPoorBuild cpPoorBuild = cpDocRepository.findOne(IdCard);
        if (cpPoorBuild!=null){
            return cpPoorBuild;
        }
        return null;
    }

    /**
     * 复制学生基本信息并返回
     * @param arg
     * @return
     */
	public CpPoorBuild getBaseInfo(CpStudent arg) {
        CpStudent cpStudent = cpStudentService.get(arg);

        if (cpStudent!=null){
            CpPoorBuild cpPoorBuild = new CpPoorBuild();
            cpPoorBuild.setCpSno(cpStudent.getCpSno());
            cpPoorBuild.setCpName(cpStudent.getCpName());
            cpPoorBuild.setCpGrade(cpStudent.getCpClass().getCpLevel());
            cpPoorBuild.setCpAcademy(cpStudent.getCpAcademy().getCpName());
            cpPoorBuild.setCpIdCardNo(cpStudent.getCpIdCardNo());
            cpPoorBuild.setCpSex(cpStudent.getCpSex());
            cpPoorBuild.setCpClass(cpStudent.getCpClass().getCpName());
            cpPoorBuild.setCpMajor(cpStudent.getCpMajor().getCpName());
            cpPoorBuild.setCpStudyLength(cpStudent.getCpMajor().getCpLength());

            return cpPoorBuild;
        }
        return null;

    }

    /**
     * 获得状态
     * 0：未开放
     * 1：当前有开放的申请
     * 2：已经申请
     * 3：老师已经审批
     * 4：教务处审批完成
     * 5:老师审核不通过
     * 6：教务处审核不通过
     * @param cpStudent
     * @return
     */
    public HashMap<String, Object> getStatus(CpStudent cpStudent) {
        HashMap<String,Object> result = new LinkedHashMap<String, Object>();
	    int status = 0;
        if(cpDocTagService.existOpen()){
	        status +=1;
            result.put("open", 1);
        }
        CpPoorBuild cpPoorBuild = getCurrentApply(cpStudent.getCpIdCardNo());
        if (cpPoorBuild!=null){
            status +=10;
            result.put("hasPost", 1);
        }else{
            //return status;
            return result;
        }
        if (cpPoorBuild.getCpCounselorResult() == 1){
            status +=100;
            result.put("cpCounselorResult", 1);
        }
        if (cpPoorBuild.getCpSuperResult() == 1){
            status +=1000;
            result.put("cpSuperResult", 1);
        }
        if(cpPoorBuild.getCpCounselorResult() == 2){
            status+=200;
            result.put("cpCounselorResult", 2);
            result.put("cpCounselorRemarks", cpPoorBuild.getCpCounselorRemarks());
        }
        if (cpPoorBuild.getCpSuperResult() == 2){
            status+=2000;
            result.put("cpSuperResult", 2);
            result.put("cpSuperRemarks", cpPoorBuild.getCpSuperRemarks());
        }
        //return status;
        return result;
    }
}//capacaty
