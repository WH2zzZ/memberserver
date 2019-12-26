package com.wanghan.microservices.memberserver.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wanghan.microservices.memberserver.web.request.RoleDeleteRequest;
import com.wanghan.microservices.memberserver.web.request.RoleSaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author WangHan
 * @Create 2019/12/26 12:40 下午
 */
@Service
public class RoleService {

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 添加角色，测试网关的熔断
     * @Author WangHan
     * @Create 12:43 下午 2019/12/26
     * @Param [request]
     * @Return void
     * @return
     */
    public String addRole(RoleSaveRequest request) throws JsonProcessingException {
        try {
            Thread.sleep(9000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return objectMapper.writeValueAsString(request);
    }

    public String deleteRole(RoleDeleteRequest request) throws JsonProcessingException {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return objectMapper.writeValueAsString(request);
    }
}
