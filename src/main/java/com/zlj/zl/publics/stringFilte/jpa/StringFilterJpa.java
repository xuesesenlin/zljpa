package com.zlj.zl.publics.stringFilte.jpa;

import com.zlj.zl.publics.stringFilte.model.StringFilterModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface StringFilterJpa extends JpaRepository<StringFilterModel, String> {

    String table = " string_filter_table ";

    @Query(value = "select * from " + table + " where yv = ?1", nativeQuery = true)
    List<StringFilterModel> pageByYv(String yv);

}
