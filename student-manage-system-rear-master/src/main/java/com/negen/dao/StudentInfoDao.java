package com.negen.dao;

import com.negen.entity.Student;
import com.negen.vo.StudentInfoVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentInfoDao extends JpaRepository<Student, Long> {
    @Query(value = "SELECT new com.negen.vo.StudentInfoVo(re.id, "
            + "ds.headTeacher) "
            + "FROM Student re left join Clazz ds on re.clazz = ds.id ORDER BY ?#{#pageable}")
    public Page<StudentInfoVo> page(Pageable pageable);

    //public List<StudentInfoVo> page();
}
