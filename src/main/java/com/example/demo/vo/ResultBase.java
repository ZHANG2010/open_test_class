package com.example.demo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 返回结果
 *
 * @author zh
 * @date 2020-06-28
 */
@Data
@Accessors(chain = true)
public class ResultBase<T> {

    /**
     * 返回编码：0-操作成功；-1-操作失败
     */
    public int code = 0;

    /**
     * 是否成功：true-成功；false-失败
     */
    public boolean success = true;

    /**
     * 详细信息:操作成功
     */
    public String message;

    /**
     * 返回结果集:接口数据
     */
    public T data;


    private ResultBase(String message, T data) {
        this.message = message;
        this.data = data;
    }

    private ResultBase(int code, boolean success, String message) {
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public static <T> ResultBase<T> success(T obj) {
        return new ResultBase<>("操作成功", obj);
    }

    public static <T> ResultBase<T> error(String message) {
        return new ResultBase<T>(-1, false, message);
    }
}
