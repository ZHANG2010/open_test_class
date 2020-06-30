package com.example.demo.repository;

import com.example.demo.entity.CoursesManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * TeacherRepository
 *
 * @author zhanghao
 * @date 2020/06/28
 */
@Repository
public interface TeacherRepository extends JpaRepository<CoursesManagement, Long> {
    /**
     * 查询课程
     *
     * @param userId 用户id
     * @param course 课程
     * @return user
     */
    @Query(value = "SELECT u.* FROM courses_management u where u.is_delete= 0 AND u.user_id=:#{#userId} AND u.course=:#{#course}", nativeQuery = true)
    CoursesManagement findByCourse(@Param("userId") String userId, @Param("course") String course);

    /**
     * 删除课程
     *
     * @param id         主键id
     * @param isDelete   是否删除
     * @param updateTime 更新时间
     * @return 返回结果
     */
    @Modifying
    @Query(value = "update courses_management u set u.is_delete=:#{#isDelete},u.update_time=:#{#updateTime} where u.id=:#{#id}", nativeQuery = true)
    int deleteCourse(@Param("id") String id, @Param("isDelete") Integer isDelete, @Param("updateTime") Date updateTime);

    /**
     * 查询课程列表
     *
     * @param userId 用户id
     * @return 返回结果
     */
    @Query(value = "SELECT u.* FROM courses_management u where u.is_delete= 0 AND u.user_id=:#{#userId}", nativeQuery = true)
    List<CoursesManagement> listCourse(@Param("userId") String userId);
}
