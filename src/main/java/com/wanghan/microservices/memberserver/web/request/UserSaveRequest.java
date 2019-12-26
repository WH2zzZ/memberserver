package com.wanghan.microservices.memberserver.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Author WangHan
 * @Create 2019/12/6 1:04 下午
 */
@Data
public class UserSaveRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
