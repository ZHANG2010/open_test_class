package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.vo.ResultBase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 用户管理接口
 *
 * @author zhanghao
 * @date 2020/06/28
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    private static final String ADMIN_USERNAME = "Admin";

    @Autowired
    private UserService userService;

    /**
     * 添加管理员
     *
     * @param user 管理员
     * @return 返回结果
     */
    @PostMapping("addAdmin")
    public ResultBase addAdmin(@RequestBody User user) {
        //添加用户
        return addUser(user);
    }

    /**
     * 添加普通用户
     *
     * @param user    普通用户
     * @param session session
     * @return 返回结果
     */
    @PostMapping("add")
    public ResultBase add(@RequestBody User user, HttpSession session) {
        try {
            //判断当前用户是否是管理员
            User currentUser = (User) session.getAttribute("currentUser");
            //只有管理员有权管理用户
            if (currentUser != null && ADMIN_USERNAME.equals(currentUser.getUsername())) {
                //添加用户
                return addUser(user);
            } else {
                return ResultBase.error("权限不足");
            }
        } catch (Exception e) {
            log.error("===================异常：" + e.getMessage());
        }
        return ResultBase.error("添加用户异常");
    }

    /**
     * 删除普通用户
     *
     * @param user    普通用户
     * @param session session
     * @return 返回结果
     */
    @PostMapping("delete")
    public ResultBase delete(@RequestBody User user, HttpSession session) {
        try {
         /*   //判断当前用户是否是管理员
            User currentUser = (User) session.getAttribute("currentUser");
            //只有管理员有权管理用户
            if (currentUser != null && ADMIN_USERNAME.equals(currentUser.getUsername())) {
                //删除用户
                return deleteUser(user);
            } else {
                return ResultBase.error("权限不足");
            }*/
            return deleteUser(user);
        } catch (Exception e) {
            log.error("===================异常：" + e.getMessage());
        }
        return ResultBase.error("删除用户异常");
    }

    /**
     * 查询用户列表
     *
     * @return 返回结果
     */
    @GetMapping("list")
    public ResultBase list() {
        try {
            log.info("===================list");
            List<User> userList = userService.list();
            if (CollectionUtils.isEmpty(userList)) {
                return ResultBase.success(null);
            } else {
                return ResultBase.success(userList);
            }
        } catch (Exception e) {
            log.error("===================查询用户列表异常：" + e.getMessage());
        }
        return ResultBase.error("查询用户列表异常");
    }


    /**
     * 删除用户
     *
     * @param user 用户
     * @return 返回结果
     */
    private ResultBase deleteUser(User user) {
        try {
            user.setIsDelete(1);
            user.setUpdateTime(new Date());
            return userService.deleteUser(user);
        } catch (Exception e) {
            log.error("===================删除用户异常：" + e.getMessage());
        }
        return ResultBase.error("删除用户异常");
    }

    /**
     * 添加用户
     *
     * @param user 用户
     * @return 返回结果
     */
    private ResultBase addUser(User user) {
        try {
            if (user == null) {
                return ResultBase.error("用户信息不能为空");
            }
            if (StringUtils.isBlank(user.getUsername())) {
                return ResultBase.error("用户名不能为空");
            }
            if (StringUtils.isBlank(user.getPassword())) {
                return ResultBase.error("密码不能为空");
            }
            if (StringUtils.isBlank(user.getUserType())) {
                return ResultBase.error("用户类型不能为空");
            }
            //判断用户名是否已存在
            User oldUser = userService.selectByUserName(user.getUsername());
            if (oldUser != null) {
                return ResultBase.error("用户名已存在");
            }
            user.setId(System.currentTimeMillis() + "" + RandomUtils.nextInt(100, 1000));
            user.setIsDelete(0);
            user.setCreateTime(new Date());
            userService.save(user);
            log.info("===================success");
            return ResultBase.success("添加用户成功");
        } catch (Exception e) {
            log.error("===================添加用户异常：" + e.getMessage());
        }
        return ResultBase.error("添加用户异常");
    }
}
