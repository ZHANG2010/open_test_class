package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * 订阅管理
 *
 * @author zhanghao
 * @date 2020-06-29
 */
@Data
@Entity
@Table(name = "subscribe_management")
public class SubscribeManagement implements Serializable {
    private static final long serialVersionUID = -2507302387073637442L;
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
     * 老师主键id
     */
    @Column(name = "teacher_id")
    private String teacherId;
    /**
     * 老师姓名
     */
    @Column(name = "teacher_name")
    private String teacherName;
    /**
     * 课程
     */
    @Column(name = "course")
    private String course;
    /**
     * 是否订阅:0:未订阅，1：已订阅
     */
    @Column(name = "subscribe")
    private Integer subscribe;
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
