package com.negen.repository;

import com.negen.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 课程Mapper
 * @author test
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    //统计班级学生人数
    List<Course> findByStudentId(String studentId);

    List<Course> findByStudentIdContaining(String content);

    @Query(value = "select * from tb_course WHERE student_name like %?1% order by start_time desc", nativeQuery=true)
    List<Course> findByStudentNameContaining(String content);

    Course findByStudentIdAndStartTime(Long studentId, Date startTime);
}
