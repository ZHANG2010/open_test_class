package com.example.demo.service.impl;

import com.example.demo.entity.SubscribeManagement;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
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

import java.util.Date;
import java.util.List;

/**
 * 订阅管理接口
 *
 * @author zhanghao
 * @date 2020/06/29
 */
@Slf4j
@Repository
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    /**
     * 添加订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    @Override
    public ResultBase addSubscribe(SubscribeManagement subscribeManagement) {
        try {
            if (subscribeManagement == null) {
                return ResultBase.error("订阅信息为空");
            }
            if (StringUtils.isBlank(subscribeManagement.getUserId())) {
                return ResultBase.error("用户id为空");
            }
            if (StringUtils.isBlank(subscribeManagement.getUsername())) {
                return ResultBase.error("用户名为空");
            }
            if (StringUtils.isBlank(subscribeManagement.getTeacherId())) {
                return ResultBase.error("老师主键id为空");
            }
            if (StringUtils.isBlank(subscribeManagement.getTeacherName())) {
                return ResultBase.error("老师姓名为空");
            }
            if (StringUtils.isBlank(subscribeManagement.getCourse())) {
                return ResultBase.error("课程为空");
            }
            //检查课程是否订阅
            SubscribeManagement oldSubscribe = studentRepository.findBySubscribe(subscribeManagement.getUserId(), subscribeManagement.getTeacherId(), subscribeManagement.getCourse());
            if (oldSubscribe != null) {
                return ResultBase.error("课程已订阅");
            }
            subscribeManagement.setId(System.currentTimeMillis() + "" + RandomUtils.nextInt(100, 1000));
            subscribeManagement.setSubscribe(1);
            subscribeManagement.setIsDelete(0);
            subscribeManagement.setCreateTime(new Date());
            studentRepository.save(subscribeManagement);
            return ResultBase.success("添加订阅成功");
        } catch (Exception e) {
            log.error("===================添加订阅异常：" + e.getMessage());
        }
        return ResultBase.error("添加订阅失败");
    }

    /**
     * 取消订阅
     *
     * @param subscribeManagement 订阅信息
     * @return 返回结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResultBase cancelSubscribe(SubscribeManagement subscribeManagement) {
        try {
            subscribeManagement.setSubscribe(0);
            subscribeManagement.setUpdateTime(new Date());
            int result = studentRepository.cancelSubscribe(subscribeManagement.getId(), subscribeManagement.getSubscribe(), subscribeManagement.getUpdateTime());
            if (result == 1) {
                log.info("===================success");
                return ResultBase.success("取消订阅成功");
            }
        } catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ResultBase.error("取消订阅失败");
    }

    /**
     * 查询每一位学生的订阅信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Override
    public ResultBase list(String userId) {
        List<SubscribeManagement> list = studentRepository.listByUserId(userId);
        if (CollectionUtils.isEmpty(list)) {
            return ResultBase.success(null);
        } else {
            return ResultBase.success(list);
        }
    }
}
