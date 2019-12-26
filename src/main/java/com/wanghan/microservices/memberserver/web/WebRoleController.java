package com.wanghan.microservices.memberserver.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wanghan.microservices.memberserver.service.RoleService;
import com.wanghan.microservices.memberserver.service.UserService;
import com.wanghan.microservices.memberserver.web.error.DefaultResponse;
import com.wanghan.microservices.memberserver.web.request.RoleDeleteRequest;
import com.wanghan.microservices.memberserver.web.request.RoleSaveRequest;
import com.wanghan.microservices.memberserver.web.request.UserSaveRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 会员web接口
 *
 * @Author WangHan
 * @Create 2019/12/6 12:52 下午
 */
@Slf4j
@RestController
@RequestMapping("/api/web/role")
public class WebRoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("")
    public DefaultResponse addRole(@Validated @RequestBody RoleSaveRequest request) throws JsonProcessingException {
        roleService.addRole(request);
        return DefaultResponse.success();
    }

    @DeleteMapping("")
    public DefaultResponse deleteRole(@Validated @RequestBody RoleDeleteRequest request) throws JsonProcessingException {
        roleService.deleteRole(request);
        return DefaultResponse.success();
    }

}
