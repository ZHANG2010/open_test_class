package com.example.demo.controller;

import com.example.demo.entity.SubscribeManagement;
import com.example.demo.service.StudentService;
import com.example.demo.vo.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 订阅管理接口
 *
 * @author zhanghao
 * @date 2020/06/29
 */
@Slf4j
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 添加订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    @PostMapping("add")
    public ResultBase add(@RequestBody SubscribeManagement subscribeManagement) {
        return studentService.addSubscribe(subscribeManagement);
    }

    /**
     * 取消订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    @PostMapping("cancel")
    public ResultBase cancel(@RequestBody SubscribeManagement subscribeManagement) {
        return studentService.cancelSubscribe(subscribeManagement);
    }

    /**
     * 查询每一位学生的订阅信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @GetMapping("list")
    public ResultBase list(String userId) {
        return studentService.list(userId);
    }
}
