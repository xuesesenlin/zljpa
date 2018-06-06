package com.zlj.zl.admin.role.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ld
 * @name 角色明细表
 * @table roleDetailed_table
 * @remarks
 */
@Entity(name = "role_detailed_table")
@Table(comment = "角色明细表", appliesTo = "role_detailed_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDetailedModel implements Serializable {
    //    uuid
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //    权限id
    @Column(name = "jurId")
    private String jurId;
    //    角色id
    @Column(name = "roleId")
    private String roleId;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getJurId() {
        return jurId;
    }

    public void setJurId(String jurId) {
        this.jurId = jurId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public RoleDetailedModel() {
        super();
    }

    public RoleDetailedModel(String uuid, String jurId, String roleId) {
        this.uuid = uuid;
        this.jurId = jurId;
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "RoleDetailedModel{" +
                "uuid='" + uuid + '\'' +
                ", jurId='" + jurId + '\'' +
                ", roleId='" + roleId + '\'' +
                '}';
    }
}
