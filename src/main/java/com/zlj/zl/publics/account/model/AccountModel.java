package com.zlj.zl.publics.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name 账户
 * @table account_table
 * @remarks
 */
@Entity(name = "account_table")
@Table(comment = "账户表", appliesTo = "account_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "账户不能为空")
    @Size(min = 6, max = 12, message = "账户长度为6-12位之间")
    @Pattern(regexp = "^[A-Za-z0-9]+$", message = "账户不能包含特殊字符")
//    name：表示数据库表中该字段的名称，默认情形属性名称一致。
//    nullable：表示该字段是否允许为null，默认为true。
//    unique：表示该字段是否是唯一标识，默认为false。
//    length：表示该字段的大小，仅对String类型的字段有效。
//    insertable：表示在ORM框架执行插入操作时，该字段是否应出现INSETRT语句中，默认为true。
//    updateable：表示在ORM框架执行更新操作时，该字段是否应该出现在UPDATE语句中，默认为true。
//    对于一经创建就不可以更改的字段，该属性非常有用，如对于birthday字段。
//    columnDefinition：表示该字段在数据库中的实际类型。通常ORM框架可以根据属性类型自动判断数据库中字段的类型，
//    但是对于Date类型仍无法确定数据库中字段类型究竟是DATE，TIME还是TIMESTAMP。此外，String的默认映射类型为VARCHAR，
//    如果要将String类型映射到特定数据库的BLOB或TEXT字段类型，该属性非常有用。
    @Column(name = "account", unique = true)
    private String account;

    @NotBlank(message = "密码不能为空")
//    @Size(min = 6, max = 12, message = "密码长度为6-12位之间")
//    @Pattern(regexp = "\\w", message = "密码不能包含特殊字符")
    @Column(name = "password")
    private String password;

    //    shiro realm验证类型
    @Column(name = "types")
    private String types;

    //    系统时间
    @Column(name = "systime", columnDefinition = "bigint")
    private long systime;

    //    0:正常记录 -1：非正常记录
    @Column(name = "flag")
    private String flag;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @DateTimeFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-ddHH:mm:ss")
    public long getSystime() {
        return systime;
    }

    public void setSystime(long systime) {
        this.systime = systime;
    }

    public AccountModel() {
        super();
    }

    public AccountModel(String uuid, String account, String password, String types, long systime, String flag) {
        this.uuid = uuid;
        this.account = account;
        this.password = password;
        this.types = types;
        this.systime = systime;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", types='" + types + '\'' +
                ", systime=" + systime +
                ", flag='" + flag + '\'' +
                '}';
    }
}
