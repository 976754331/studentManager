package com.negen.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.negen.entity.Clazz;
import com.negen.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 课程Mapper
 * @author test
 */
@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query(value = "select p.* from tb_price p \n" +
            "LEFT JOIN tb_class_price cp on cp.price_id = p.id\n" +
            "LEFT JOIN tb_clazz c on c.id = cp.class_id\n" +
            "LEFT JOIN tb_student s on s.clazz = c.clazz\n" +
            "WHERE s.id=?1", nativeQuery=true)
    Price findPriceWithStudent(Long studentId);

}
