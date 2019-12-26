package com.wanghan.microservices.memberserver.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wanghan.microservices.memberserver.service.UserService;
import com.wanghan.microservices.memberserver.web.error.DefaultResponse;
import com.wanghan.microservices.memberserver.web.request.UserSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会员web接口
 *
 * @Author WangHan
 * @Create 2019/12/6 12:52 下午
 */
@Slf4j
@RestController
@RequestMapping("/api/web/user")
public class WebUserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public DefaultResponse<String> register(@Validated @RequestBody UserSaveRequest request) throws JsonProcessingException {
        String token = userService.addUser(request);
        return DefaultResponse.success(token);
    }

    @PostMapping("/login")
    public DefaultResponse<String> login(@Validated @RequestBody UserSaveRequest request) throws JsonProcessingException {
        String token = userService.login(request);
        return DefaultResponse.success(token);
    }

}
