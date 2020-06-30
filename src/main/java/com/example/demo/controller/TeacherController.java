package com.example.demo.controller;

import com.example.demo.entity.CoursesManagement;
import com.example.demo.service.TeacherService;
import com.example.demo.vo.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 课程管理接口
 *
 * @author zhanghao
 * @date 2020/06/29
 */
@Slf4j
@RestController
@RequestMapping("teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    /**
     * 添加课程
     *
     * @param coursesManagement 课程信息
     * @return 返回结果
     */
    @PostMapping("add")
    public ResultBase add(@RequestBody CoursesManagement coursesManagement) {
        return teacherService.addCourse(coursesManagement);
    }

    /**
     * 删除课程
     *
     * @param coursesManagement 课程信息
     * @return 返回结果
     */
    @PostMapping("delete")
    public ResultBase delete(@RequestBody CoursesManagement coursesManagement) {
        return teacherService.deleteCourse(coursesManagement);
    }

    /**
     * 查询课程列表
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @GetMapping("list")
    public ResultBase list(String userId) {
        return teacherService.listCourse(userId);
    }

}
