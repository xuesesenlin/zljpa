package com.zlj.zl.publics.token.jpa;

import com.zlj.zl.publics.token.model.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface TokenJpa extends JpaRepository<TokenModel, String> {

    @Modifying
    @Query(value = "update token_table set is_use = 'Y' where token = ?1", nativeQuery = true)
    @Transactional
    void updateToken(String token);

    @Query(value = "select uuid,account,token,end_time,is_use from token_table " +
            "where token = ?1 ORDER BY end_time desc LIMIT 1", nativeQuery = true)
    TokenModel getByToken(String token);
}
