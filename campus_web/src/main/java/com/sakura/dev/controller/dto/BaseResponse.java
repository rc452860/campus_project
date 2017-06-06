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

    public static BaseResponse OK(Object data) {
        return OK(null,data);
    }

    public static BaseResponse OK(String descript,Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setData(data);
        baseResponse.setStatus(SUCCESS);
        baseResponse.setDescript(descript);
        return baseResponse;
    }

    public static BaseResponse ERR(String descript) {
        return ERR(descript, null);
    }

    public static BaseResponse ERR(String descript,Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(ERROR);
        baseResponse.setDescript(descript);
        baseResponse.setData(data);
        return baseResponse;
    }
    public static BaseResponse FAILD(String descript) {
        return FAILD(descript, null);
    }
    public static BaseResponse FAILD(String descript,Object data){
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setDescript(descript);
        baseResponse.setStatus(ERROR);
        baseResponse.setData(data);
        return baseResponse;
    }
}
