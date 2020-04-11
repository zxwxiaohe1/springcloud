package com.self.eureka.discovery.interfaces.clientinterface.dto;

import org.springframework.stereotype.Component;

/**
 * Created by zengjing on 2018/4/13.
 */
@Component
public class Result<T> {

    /**
     * 返回编码
     */
    private int code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据体
     */
    private T data;

    public Result(T object) {
        this.setData(object);
    }

    public Result() {
        this.code = 0;
        this.setMessage("操作成功");
    }

    public Result sucess() {
        this.setCode(0);
        this.setMessage("操作成功");
        return this;
    }

    public Result sucess(String message) {
        this.setCode(0);
        this.setMessage(message);
        return this;
    }

    public Result sucess(String message, T data) {
        this.setCode(0);
        this.setMessage(message);
        this.setData(data);
        return this;
    }

    public Result sucess(T data) {
        this.setCode(0);
        this.setMessage("操作成功!");
        this.setData(data);
        return this;
    }

    public Result error() {
        this.setCode(500);
        this.setMessage("操作失败!");
        return this;
    }

    public Result error(String message) {
        this.setCode(500);
        this.setMessage(message);
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
