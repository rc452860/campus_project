package com.sakura.dev.controller.dto;

import lombok.Data;

/**
 * Created by yth on 2017/6/7.
 */
@Data
public class AuditReuqest {
    private Long cpId;   //审核学生
    private Boolean result;
    private String remark;  //审核结果
}
