package com.example.demo.service;

import com.example.demo.entity.CoursesManagement;
import com.example.demo.vo.ResultBase;

/**
 * 课程管理接口
 *
 * @author zh
 * @date 2020/06/29
 */
public interface TeacherService {

    /**
     * 添加课程
     *
     * @param coursesManagement 课程
     * @return 返回结果
     */
    ResultBase addCourse(CoursesManagement coursesManagement);

    /**
     * 删除课程
     *
     * @param coursesManagement 课程信息
     * @return 返回结果
     */
    ResultBase deleteCourse(CoursesManagement coursesManagement);

    /**
     * 查询课程列表
     *
     * @param userId 用户id
     * @return 返回结果
     */
    ResultBase listCourse(String userId);
}
