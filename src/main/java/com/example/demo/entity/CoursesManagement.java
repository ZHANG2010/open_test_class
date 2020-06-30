package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程管理
 *
 * @author zhanghao
 * @date 2020-06-29
 */
@Data
@Entity
@Table(name = "courses_management")
public class CoursesManagement implements Serializable {
    private static final long serialVersionUID = -3053514479135245659L;
    /**
     * 主键id
     */
    @Id
    @Column(name = "id", insertable = false)
    private String id;
    /**
     * 用户表主键id
     */
    @Column(name = "user_id")
    private String userId;
    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;
    /**
     * 课程
     */
    @Column(name = "course")
    private String course;
    /**
     * 订阅数量
     */
    @Column(name = "number")
    private Integer number;
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
