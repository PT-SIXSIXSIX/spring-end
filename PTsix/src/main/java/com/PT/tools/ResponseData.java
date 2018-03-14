package com.PT.tools;
import java.util.HashMap;
import java.util.Map;

/**
 * created by yxhuang
 * 快速返回信息类，包括很多常用操作
 */
public class ResponseData {
    private String message;
    private int code;
    private Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
    public Map<String, Object> getData() {
        return data;
    }
    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }
    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }
    public void setError(int status, String message) {
        data.put("statusCode", status);
        data.put("errorDesc", message);
    }
    public Map getBody() {
        return this.data;
    }
    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }
    public static ResponseData createOk() {
        return new ResponseData(201, "Ok");
    }
    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }
    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }
    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }
    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }
    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }
    public static ResponseData customerError() {
        return new ResponseData(1001, "customer Error");
    }
}