package com.sakura.dev.controller.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Created by rc452 on 2017/6/5.
 */
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Result {
    public static String SUCCESS = "success";
    public static String ERROR ="error";
    public static String FAILD = "faild";
    private String status = FAILD;
    private String descript;
    private Object data;

    public static Result OK(Object data) {
        return OK(null,data);
    }

    public static Result OK(String descript, Object data){
        Result result = new Result();
        result.setData(data);
        result.setStatus(SUCCESS);
        result.setDescript(descript);
        return result;
    }

    public static Result ERR(String descript) {
        return ERR(descript, null);
    }

    public static Result INFO(String descript) {
        Result result = new Result();
        result.setStatus(SUCCESS);
        result.setDescript(descript);
        return result;
    }

    public static Result ERR(String descript, Object data){
        Result result = new Result();
        result.setStatus(ERROR);
        result.setDescript(descript);
        result.setData(data);
        return result;
    }
    public static Result FAILD(String descript) {
        return FAILD(descript, null);
    }

    public static Result FAILD(String descript, Object data){
        Result result = new Result();
        result.setDescript(descript);
        result.setStatus(ERROR);
        result.setData(data);
        return result;
    }
}
