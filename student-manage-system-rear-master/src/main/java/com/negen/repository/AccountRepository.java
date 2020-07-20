package com.negen.repository;

import com.negen.entity.Account;
import com.negen.entity.Course;
import com.negen.entity.Price;
import com.negen.vo.AccountExportVo;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 账单Mapper
 * @author test
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByStudentId(String studentId);
    @Query(value = "SELECT * FROM tb_account where student_id=?1 order by create_time desc limit 1", nativeQuery=true)
    Account findLastAccount(Long studentId);

    @Query(value = "select a.* from tb_account a \n" +
            "LEFT JOIN tb_student s on s.id = a.student_id\n" +
            "WHERE s.name like %?1% order by a.create_time DESC", nativeQuery=true)
    List<Account> findByStudentNameContaining(String content);

    @Query(value = "SELECT\n" +
            "\ta.student_name,\n" +
            "\ta.type,\n" +
            "\ta.total_amount,\n" +
            "\ta.used_amount,\n" +
            "a.create_time,\n" +
            "a.start_time,\n" +
            "c.teacher_id,\n" +
            "c.course_content,\n" +
            "c.class_id,\n" +
            "cc.grade,\n" +
            "pp.display\n" +
            "FROM\n" +
            "\ttb_account a\n" +
            "LEFT JOIN tb_course c ON a.course_id = c.id\n" +
            "LEFT JOIN tb_clazz cc ON c.class_id = cc.clazz\n" +
            "LEFT JOIN tb_class_price cp ON cp.class_id = cc.id\n" +
            "LEFT JOIN tb_price pp ON pp.id = cp.price_id\n" +
            "ORDER BY\n" +
            "\ta.create_time DESC", nativeQuery=true)
    List<AccountExportVo> findAllAndExport();

    Account findByCourseId(Long id);
}
