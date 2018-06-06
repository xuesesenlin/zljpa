package com.zlj.zl.admin.jurisdiction.jpa;

import com.zlj.zl.admin.jurisdiction.model.JurisdictionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public interface JurisdictionJpa extends JpaRepository<JurisdictionModel, String> {

    String table = " jurisdiction_table ";

    List<JurisdictionModel> findByParents(String parents);

    List<JurisdictionModel> findBySysType(String sysType);

    List<JurisdictionModel> findByJurType(int jurType);

    List<JurisdictionModel> findByParentsAndSysTypeAndJurType(String parents,
                                                              String sysType,
                                                              long jurType);

    //    @Modifying
    @Query(value = "select * from jurisdiction_table order by name", nativeQuery = true)
    List<JurisdictionModel> page();

    @Query(value = "select * from jurisdiction_table where name = #{name} order by name", nativeQuery = true)
    List<JurisdictionModel> pageByName(String name);

    @Query(value = "select j.* from role_detailed_table r left join jurisdiction_table j " +
            "on j.uuid = r.jur_id where role_id = ?1", nativeQuery = true)
    List<JurisdictionModel> findByRoleId(String roleId);


}
