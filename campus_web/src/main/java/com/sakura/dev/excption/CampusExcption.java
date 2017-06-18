package com.sakura.dev.excption;

/**
 * Created by rc452 on 2017/6/18.
 */
public class CampusExcption extends RuntimeException {
    public CampusExcption(){}
    public CampusExcption(String message){
        super(message);
    }
}
