package com.negen.repository;

import com.negen.entity.Account;
import com.negen.vo.AccountExportVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * 账单Mapper
 * @author test
 */

public interface AccountManager extends EntityManager {

}
