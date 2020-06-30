package com.example.demo.service.impl;

import com.example.demo.entity.CoursesManagement;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.service.TeacherService;
import com.example.demo.vo.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.CollectionUtils;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 课程管理接口
 *
 * @author zhanghao
 * @date 2020/06/29
 */
@Slf4j
@Repository
@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 添加课程
     *
     * @param coursesManagement 课程
     * @return 返回结果
     */
    @Override
    public ResultBase addCourse(CoursesManagement coursesManagement) {
        try {
            if (coursesManagement == null) {
                return ResultBase.error("课程信息为空");
            }
            if (StringUtils.isBlank(coursesManagement.getUserId())) {
                return ResultBase.error("用户id为空");
            }
            if (StringUtils.isBlank(coursesManagement.getUsername())) {
                return ResultBase.error("用户名为空");
            }
            if (StringUtils.isBlank(coursesManagement.getCourse())) {
                return ResultBase.error("课程为空");
            }
            //检查课程是否存在
            CoursesManagement oldCourse = teacherRepository.findByCourse(coursesManagement.getUserId(), coursesManagement.getCourse());
            if (oldCourse != null) {
                return ResultBase.error("课程已存在");
            }
            coursesManagement.setId(System.currentTimeMillis() + "" + RandomUtils.nextInt(100, 1000));
            coursesManagement.setNumber(0);
            coursesManagement.setIsDelete(0);
            coursesManagement.setCreateTime(new Date());
            teacherRepository.save(coursesManagement);
            return ResultBase.success("添加课程成功");
        } catch (Exception e) {
            log.error("===================添加用户异常：" + e.getMessage());
        }
        return ResultBase.error("添加课程失败");
    }

    /**
     * 删除课程
     *
     * @param coursesManagement 课程信息
     * @return 返回结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBase deleteCourse(CoursesManagement coursesManagement) {
        try {
            coursesManagement.setIsDelete(1);
            coursesManagement.setUpdateTime(new Date());
            int result = teacherRepository.deleteCourse(coursesManagement.getId(), coursesManagement.getIsDelete(), coursesManagement.getUpdateTime());
            if (result == 1) {
                log.info("===================success");
                return ResultBase.success("删除课程成功");
            }
        } catch (Exception e) {
            log.error("==========删除课程异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ResultBase.error("删除课程失败");
    }

    /**
     * 查询课程列表
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Override
    public ResultBase listCourse(String userId) {
        try {
            List<CoursesManagement> list = teacherRepository.listCourse(userId);
            if (CollectionUtils.isEmpty(list)) {
                return ResultBase.success(null);
            } else {
                //查询每个课程的订阅数量
                List<Map<String, Object>> countList = studentRepository.countByUserId(userId);
                if (!CollectionUtils.isEmpty(countList)) {
                    for (Map<String, Object> map : countList) {
                        log.info("==========查询每个课程的订阅数量: " + map.get("COUNTS"));
                        log.info("==========老师id: " + map.get("TEACHER_ID"));
                        log.info("==========课程: " + map.get("COURSE"));

                        Integer counts = ((BigInteger) map.get("COUNTS")).intValue();
                        String teacherId = (String) map.get("TEACHER_ID");
                        String course = (String) map.get("COURSE");
                        for (CoursesManagement coursesManagement : list) {
                            if (coursesManagement != null) {
                                if (StringUtils.isNotBlank(coursesManagement.getUserId()) && StringUtils.isNotBlank(coursesManagement.getCourse())) {
                                    if (coursesManagement.getUserId().equals(teacherId) && coursesManagement.getCourse().equals(course)) {
                                        Integer number = coursesManagement.getNumber();
                                        coursesManagement.setNumber(number + counts);
                                    }
                                }
                            }
                        }
                    }
                }
                return ResultBase.success(list);
            }
        } catch (Exception e) {
            log.error("==========查询课程列表异常：" + e.getMessage());
        }
        return ResultBase.error("查询课程列表失败");
    }
}
