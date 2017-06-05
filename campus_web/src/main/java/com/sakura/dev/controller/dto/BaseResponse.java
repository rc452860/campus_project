package com.sakura.dev.controller.dto;

import lombok.Data;

/**
 * Created by rc452 on 2017/6/5.
 */
@Data
public class BaseResponse {
    private String status = FAILD;
    private String descript;
    private Object data;

    public static String SUCCESS = "success";
    public static String ERROR ="error";
    public static String FAILD = "faild";
}
