package com.negen.repository;

import com.negen.entity.Account;
import com.negen.entity.ClassPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账单Mapper
 * @author test
 */
@Repository
public interface ClassPriceRepository extends JpaRepository<ClassPrice, Long> {

}
