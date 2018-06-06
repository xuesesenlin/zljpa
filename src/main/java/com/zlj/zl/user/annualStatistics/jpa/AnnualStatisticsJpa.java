package com.zlj.zl.user.annualStatistics.jpa;

import com.zlj.zl.user.patentDataPut.model.PatentDataPutModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface AnnualStatisticsJpa extends JpaSpecificationExecutor<PatentDataPutModel>,
        JpaRepository<PatentDataPutModel, String> {

//    //    按年度统计
//    //    申请情况
//    @Query(value = "select count(*),from_unixtime(p.imp_date/1000,'%Y-%m') imp_date,p.apply_authorization " +
//            "from patent_data_put_table p where from_unixtime(p.imp_date/1000,'%Y-%m') = '2018-04' " +
//            "group by p.imp_date,p.apply_authorization",
//            nativeQuery = true)
//    List<Object[]> findDate1(String impDate);
}
