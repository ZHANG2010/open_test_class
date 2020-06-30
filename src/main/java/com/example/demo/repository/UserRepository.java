package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * userRepository
 *
 * @author zhanghao
 * @date 2020/06/28
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 通过username查询用户
     *
     * @param username 用户名
     * @return user
     */
    @Query(value = "SELECT u.* FROM USER u where u.is_delete= 0 AND u.username=:#{#username}", nativeQuery = true)
    User findByUsername(@Param("username") String username);

    /**
     * 查询用户列表
     *
     * @return list
     */
    @Query(value = "SELECT u.* FROM USER u where u.is_delete= 0", nativeQuery = true)
    List<User> userList();

    /**
     * 删除用户
     *
     * @param id         主键id
     * @param isDelete   是否删除
     * @param updateTime 更新时间
     * @return 返回结果
     */
    @Modifying
    @Query(value = "update USER u set u.is_delete=:#{#isDelete},u.update_time=:#{#updateTime} where u.id=:#{#id}", nativeQuery = true)
    int deleteUser(@Param("id") String id, @Param("isDelete") Integer isDelete, @Param("updateTime") Date updateTime);
}
