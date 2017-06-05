package com.sakura.dev.controller.dto;

import lombok.Data;

/**
 * Created by rc452 on 2017/6/5.
 */
@Data
public class LoginRequest {
    private String username;
    private String password;
    private String vaildcode;
}
