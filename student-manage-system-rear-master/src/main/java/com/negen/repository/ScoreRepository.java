package com.negen.repository;

import com.negen.entity.Course;
import com.negen.entity.Score;
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
public interface ScoreRepository extends JpaRepository<Score, Long> {
    @Query(value = "select * from tb_score WHERE student_name like %?1% order by exam_time desc", nativeQuery=true)
    List<Score> findByStudentNameContaining(String content);

    List<Score> findByStudentIdContaining(String content);
}
