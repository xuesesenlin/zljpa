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
 * @name 角色与账户的关联关系
 * @table role_acc_table
 * @remarks
 */
@Entity(name = "role_acc_table")
@Table(comment = "角色账户表", appliesTo = "role_acc_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleAccModel implements Serializable {

    //    uuid
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //    角色id
    @Column(name = "role_id")
    private String roleId;
    //    账户id
    @Column(name = "account_id")
    private String accountId;
    //    flag
    @Column(name = "flag")
    private String flag;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public RoleAccModel() {
        super();
    }

    public RoleAccModel(String uuid, String roleId, String accountId, String flag) {
        this.uuid = uuid;
        this.roleId = roleId;
        this.accountId = accountId;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "RoleAccModel{" +
                "uuid='" + uuid + '\'' +
                ", roleId='" + roleId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", flag='" + flag + '\'' +
                '}';
    }
}
