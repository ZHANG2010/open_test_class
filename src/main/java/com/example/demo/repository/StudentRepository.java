package com.example.demo.repository;

import com.example.demo.entity.SubscribeManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * StudentRepository
 *
 * @author zhanghao
 * @date 2020/06/29
 */
@Repository
public interface StudentRepository extends JpaRepository<SubscribeManagement, Long> {
    /**
     * 查询课程的订阅信息
     *
     * @param userId    用户id
     * @param teacherId 老师id
     * @param course    课程
     * @return SubscribeManagement
     */
    @Query(value = "SELECT u.* FROM subscribe_management u where u.is_delete= 0 AND u.user_id=:#{#userId} AND u.teacher_id=:#{#teacherId} AND u.course=:#{#course}", nativeQuery = true)
    SubscribeManagement findBySubscribe(@Param("userId") String userId, @Param("teacherId") String teacherId, @Param("course") String course);

    /**
     * 取消订阅
     *
     * @param id         主键id
     * @param subscribe  是否订阅
     * @param updateTime 更新时间
     * @return 返回结果
     */
    @Modifying
    @Query(value = "update subscribe_management u set u.subscribe=:#{#subscribe},u.update_time=:#{#updateTime} where u.is_delete= 0 AND u.id=:#{#id}", nativeQuery = true)
    int cancelSubscribe(@Param("id") String id, @Param("subscribe") Integer subscribe, @Param("updateTime") Date updateTime);

    /**
     * 分组查询每一位老师的课程的订阅信息
     *
     * @param teacherId 老师id
     * @return 返回结果
     */
    @Query(value = "SELECT count(u.id) AS counts ,u.teacher_id, u.course FROM subscribe_management u where u.is_delete= 0 AND u.teacher_id=:#{#teacherId} AND u.subscribe= 1 GROUP BY u.teacher_id, u.course", nativeQuery = true)
    List<Map<String, Object>> countByUserId(@Param("teacherId") String teacherId);

    /**
     * 查询每一位学生的订阅信息
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Query(value = "SELECT u.* FROM subscribe_management u where u.is_delete= 0 AND u.user_id=:#{#userId} AND u.subscribe= 1", nativeQuery = true)
    List<SubscribeManagement> listByUserId(@Param("userId") String userId);
}
