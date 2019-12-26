package com.wanghan.microservices.memberserver.web.request;

import lombok.Data;

/**
 * @Author WangHan
 * @Create 2019/12/26 12:42 下午
 */
@Data
public class RoleDeleteRequest {

    private String uid;

    private String roleId;

}
