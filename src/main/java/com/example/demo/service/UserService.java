package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.ResultBase;

import java.util.List;

/**
 * 用户管理接口
 *
 * @author zh
 * @date 2020/06/28
 */
public interface UserService {

    void save(User user);

    ResultBase deleteUser(User user);

    List<User> list();

    User selectByUserName(String username);
}
