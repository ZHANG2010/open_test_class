package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.vo.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * 用户管理接口
 *
 * @author zh
 * @date 2020/06/28
 */
@Slf4j
@Repository
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultBase deleteUser(User user) {
        try {
            int result = userRepository.deleteUser(user.getId(), user.getIsDelete(), user.getUpdateTime());
            if (result == 1) {
                log.info("===================success");
                return ResultBase.success("删除用户成功");
            }
        } catch (Exception e) {
            log.error("==========删除用户异常：" + e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return ResultBase.error("删除用户异常");
    }

    @Override
    public List<User> list() {
        return userRepository.userList();
    }

    @Override
    public User selectByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
