package com.example.demo.service;

import com.example.demo.entity.SubscribeManagement;
import com.example.demo.vo.ResultBase;

/**
 * 订阅管理接口
 *
 * @author zh
 * @date 2020/06/29
 */
public interface StudentService {
    /**
     * 添加订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    ResultBase addSubscribe(SubscribeManagement subscribeManagement);

    /**
     * 取消订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    ResultBase cancelSubscribe(SubscribeManagement subscribeManagement);

    /**
     * 查询每一位学生的订阅信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    ResultBase list(String userId);
}
