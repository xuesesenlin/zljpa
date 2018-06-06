package com.zlj.zl.user.monthlyReport.jpa;

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
public interface MonthlyReportJpa extends JpaSpecificationExecutor<PatentDataPutModel>,
        JpaRepository<PatentDataPutModel, String> {

    //    全市专利申请受理、批准数量统计表
    //    本年本月
    @Query(value = "select count(*),p.patent_types,p.app_peo_types,p.apply_authorization from patent_data_put_table p " +
            " where from_unixtime(p.imp_date/1000,'%Y-%m') = ?1 group by p.patent_types,p.app_peo_types",
            nativeQuery = true)
    List<Object[]> findDate1(String impDate);

    //    本年本月止累计
    @Query(value = "select count(*),p.patent_types,p.app_peo_types,p.apply_authorization from patent_data_put_table p " +
            "where from_unixtime(p.imp_date/1000,'%Y') = ?1 group by p.patent_types,p.app_peo_types",
            nativeQuery = true)
    List<Object[]> findDate2(String impDate);

    //    1985年4月以来总累计
    @Query(value = "select count(*),p.patent_types,p.app_peo_types,p.apply_authorization from patent_data_put_table p " +
            "where from_unixtime(p.imp_date/1000,'%Y-%m') >= ?1 group by p.patent_types,p.app_peo_types",
            nativeQuery = true)
    List<Object[]> findDate3(String impDate);

    //    各县市（区）专利申请、授权、有效情况
//    本年本月止
    @Query(value = "select count(*),p.area,p.apply_authorization from patent_data_put_table p where " +
            " from_unixtime(p.imp_date/1000,'%Y-%m') = ?1 group by p.area,p.apply_authorization",
            nativeQuery = true)
    List<Object[]> findDate4(String impDate);

    @Query(value = "select count(*),p.area,p.apply_authorization from patent_data_put_table p where " +
            " from_unixtime(p.imp_date/1000,'%Y') = ?1 group by p.area,p.apply_authorization",
            nativeQuery = true)
    List<Object[]> findDate5(String impDate);
}
