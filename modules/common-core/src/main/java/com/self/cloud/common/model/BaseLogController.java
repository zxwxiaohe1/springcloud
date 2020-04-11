package com.self.cloud.common.model;

/**
 * @author HY
 * @date 2019/6/17 17:00
 */
public abstract class BaseLogController {


    public <T> ReturnResult<T> success(int status, String message, T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(status);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }

    public <T> ReturnResult<T> success(String message, T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }
    public <T> ReturnResult<T> success(String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setMessage(message);
        return returnResult;
    }
    public <T> ReturnResult<T> success(T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setData(data);
        return returnResult;
    }

    public <T> ReturnResult<T> error(int status, String message, T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(500);
        returnResult.setStatus(status);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }

    public <T> ReturnResult<T> error(String message, T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(500);
        returnResult.setMessage(message);
        returnResult.setData(data);
        return returnResult;
    }
    public <T> ReturnResult<T> error(String message) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(500);
        returnResult.setMessage(message);
        return returnResult;
    }
    public <T> ReturnResult<T> error(T data) {
        ReturnResult returnResult = new ReturnResult();
        returnResult.setStatus(500);
        returnResult.setData(data);
        return returnResult;
    }
    public <T> ReturnResult<T> returnResult() {
        return new ReturnResult();
    }
}
