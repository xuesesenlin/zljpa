package com.zlj.zl.user.PMC.jpa;

import com.zlj.zl.user.PMC.model.PMCModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PMCJpa extends JpaSpecificationExecutor<PMCModel>, JpaRepository<PMCModel, String> {

    String table = " pmc_table ";

    @Modifying
    @Query(value = "update " + table + " set names = ?1 where uuid = ?2", nativeQuery = true)
    @Transactional
    void update(String names, String uuid);

    /**
     * 根据城市名称返回所有的区
     *
     * @param names
     * @return
     */
    @Query(value = "select * from pmc_table where patent_uuid in (" +
            "select p.uuid from pmc_table p where p.names = ?1)", nativeQuery = true)
    List<PMCModel> findByCityName(String names);
}
