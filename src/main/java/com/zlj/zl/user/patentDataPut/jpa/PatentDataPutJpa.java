package com.zlj.zl.user.patentDataPut.jpa;

import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface PatentDataPutJpa extends JpaSpecificationExecutor<PatentDataPutModel>,
        JpaRepository<PatentDataPutModel, String> {

    //    @Modifying
//    @Query(value = "update " + table + " set keyworda = :model.,keywordb = ?2 where uuid = ?3")
//    @Transactional
//    按照pojo规则不用上述标签,使用pojo规范注意方法名必须和属性名对应,首字母采用set，get规则，必须参考此规则
    List<PatentDataPutModel> findByApplyCode(String applyCode);

    @Query(value = "select p.imp_date as impDate,p.apply_authorization as applyAuthorization from " +
            " patent_data_put_table p GROUP BY p.imp_date,p.apply_authorization ORDER BY p.imp_date desc LIMIT ?1,?2",
            nativeQuery = true)
    List<Object[]> findDate(int pageNow, int pageSize);

    @Query(value = "select p.imp_date as impDate,p.apply_authorization as applyAuthorization from " +
            "             patent_data_put_table p where from_unixtime(p.imp_date/1000,'%Y-%m') >= ?3" +
            "             and from_unixtime(p.imp_date/1000,'%Y-%m') <= ?4" +
            "            GROUP BY p.imp_date,p.apply_authorization ORDER BY p.imp_date desc LIMIT ?1,?2",
            nativeQuery = true)
    List<Object[]> findDateByDate(int pageNow, int pageSize, String drnyStar, String drnyEnd);

    List<PatentDataPutModel> findByImpDate(long impDate);
}
