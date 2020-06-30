package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 登录接口
 *
 * @author zhanghao
 * @date 2020/06/28
 */
@Slf4j
@RestController
public class LoginController {

    private static final String USER_TYPE_ADMIN = "0";
    private static final String USER_TYPE_TEAC = "1";
    private static final String USER_TYPE_STU = "2";

    @Autowired
    private UserService userService;

    @GetMapping("login")
    public String login(String username, String password, HttpSession session) {
        if (StringUtils.isBlank(username)) {
            return "/login";
        }
        if (StringUtils.isBlank(password)) {
            return "/login";
        }
        User user = userService.selectByUserName(username);
        if (user != null) {
            log.info("===============user: " + user.toString());
            if (password.equals(user.getPassword()) && user.getIsDelete() == 0) {
                //判断用户类型
                if (USER_TYPE_ADMIN.equals(user.getUserType())) {
                    session.setAttribute("currentUser", user);
                    //跳转到管理员页面
                    return "/admin";
                }
                if (USER_TYPE_TEAC.equals(user.getUserType())) {
                    //跳转到课程页面
                    return "/teacher";
                }
                if (USER_TYPE_STU.equals(user.getUserType())) {
                    //跳转到订阅页面
                    return "/student";
                }
            }
        }
        return "/login";
    }
}
