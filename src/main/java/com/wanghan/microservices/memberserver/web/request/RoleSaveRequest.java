package com.wanghan.microservices.memberserver.web.request;

import lombok.Data;

import java.util.List;

/**
 * @Author WangHan
 * @Create 2019/12/26 12:41 下午
 */
@Data
public class RoleSaveRequest {

    private String uid;

    private List<Integer> roleId;
}
