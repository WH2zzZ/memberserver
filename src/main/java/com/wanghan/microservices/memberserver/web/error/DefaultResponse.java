package com.wanghan.microservices.memberserver.web.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 自定义返回值
 *
 * @Author WangHan
 * @Create 2019/12/6 12:56 下午
 */
@Data
public class DefaultResponse<T> {

    protected static final int SUCCESS_STATUS = 200;
    protected static final String SUCCESS_MESSAGE = "success";
    
    @JsonProperty("data")
    private T data;
    @JsonProperty("message")
    private String message;
    @JsonProperty("status")
    private int code;

    public static <T> DefaultResponse<T> success() {
        return success(null);
    }

    public static <T> DefaultResponse<T> success(T data) {
        return success(data, "success", "");
    }

    public static <T> DefaultResponse<T> success(T data, String message, String moreInfo) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setData(data);
        response.setCode(SUCCESS_STATUS);
        response.setMessage(message);
        return response;
    }

    public static <T> DefaultResponse<T> error(int code, String message, String moreInfo) {
        DefaultResponse<T> response = new DefaultResponse<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> DefaultResponse<T> error(int responseStatus, String message) {
        return error(responseStatus, message, (String)null);
    }

    public static <T> DefaultResponse<T> error(int responseStatus) {
        return error(responseStatus, (String)null, (String)null);
    }
}
