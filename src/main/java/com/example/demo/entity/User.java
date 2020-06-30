package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体类
 *
 * @author zhanghao
 * @date 2020-06-28
 */
@Data
@Entity
@Table(name = "USER")
public class User implements Serializable {
    private static final long serialVersionUID = 5780595058487857777L;
    /**
     * 主键id
     */
    @Id
    @Column(name = "id", insertable = false)
    private String id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    /**
     * 用户类型：0：管理员，1：教师 ，2：学生
     */
    @Column(name = "user_type")
    private String userType;
    /**
     * 是否删除：0：否，1：是
     */
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column(name = "create_time")
    private Date createTime;
    @Column(name = "update_time")
    private Date updateTime;
}
