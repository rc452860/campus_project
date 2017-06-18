package com.sakura.dev.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by rc452 on 2017/6/6.
 */
@Data
@Entity
public class CpDocTag {
    @Id
    @GeneratedValue
    private Long cpId;
    private String cpName;   //名称
    private Date cpStart;    //开放时间
    private Date cpEnd;    //结束时间

    public CpDocTag(){}

    public CpDocTag(String cpName, Date cpStart, Date cpEnd) {
        this.cpName = cpName;
        this.cpStart = cpStart;
        this.cpEnd = cpEnd;
    }
}
