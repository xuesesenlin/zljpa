package com.zlj.zl.publics.account.jpa;

import com.zlj.zl.publics.account.model.AccountModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AccountJpa extends JpaRepository<AccountModel, String> {

    String table = " account_table ";

    /**
     * nativeQuery = true; 使用源生sql，默认为hql语句
     *
     * @param account
     * @return
     */
//    增删改 需要配合此注解
//    @Modifying
    @Query(value = "select * from " + table + " where account = ?1", nativeQuery = true)
    AccountModel getByAccount(String account);

    @Modifying
    @Query(value = "update " + table + " set password = ?2 where account = ?1", nativeQuery = true)
    void updatePWD(String account, String password);

    @Query(value = "select * from " + table + " where account = ?1 and types = ?2", nativeQuery = true)
    AccountModel getByAccountAndTypes(String account,
                                      String types);

    @Query(value = "select * from " + table + " where types = 'user'", nativeQuery = true)
    List<AccountModel> find();

    @Query(value = " SELECT"
            + " 	a.uuid,"
            + " 	a.account,"
            + " 	'' AS PASSWORD,"
            + " 	a.flag,"
            + " 	("
            + " 		CASE"
            + " 		WHEN a.types = 'superAdmin' THEN"
            + " 			'超级管理员'"
            + " 		ELSE"
            + " 			("
            + " 				CASE"
            + " 				WHEN a.types = 'admin' THEN"
            + " 					'管理员'"
            + " 				ELSE"
            + " ("
            + " 	CASE"
            + " 	WHEN a.types = 'user' THEN"
            + " 		'普通用户'"
            + " 	ELSE"
            + " 		'普通用户'"
            + " 	END"
            + " )"
            + " 				END"
            + " 			)"
            + " 		END"
            + " 	) AS types,"
            + " 	a.systime"
            + " FROM"
            + " 	account_table a"
            + " WHERE"
            + " 	a.flag = '0'"
            + " ORDER BY"
            + " 	?#{#pageable},"
            + " 	a.account",
            countQuery = "select count(*) from " + table + "  where flag = '0'",
            nativeQuery = true)
    Page<AccountModel> page(Pageable pageable);

    @Query(value = "select * from " + table + " where account = ?1", nativeQuery = true)
    List<AccountModel> pageByAccount(String account);

    @Query(value = "select * from account_table where uuid NOT in (" +
            "select acc_id from organ_acc_table where flag = '0') and types = 'user' ORDER BY account", nativeQuery = true)
    List<AccountModel> findByNotOrgan();
}
