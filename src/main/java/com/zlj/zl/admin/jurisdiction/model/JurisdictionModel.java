package com.zlj.zl.admin.jurisdiction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name 权限
 * @table jurisdiction_table
 * @remarks
 */
@Entity(name = "jurisdiction_table")
@Table(comment = "权限表", appliesTo = "jurisdiction_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class JurisdictionModel implements Serializable {

    //    uuid
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Size(max = 255, message = "主键长度超出范围")
    private String uuid;
    //    权限名称
    @NotBlank(message = "权限名称不能为空")
    @Size(max = 255, message = "权限名称长度超出范围,长度范围为100位之内")
    @Column(name = "name")
    private String name;
    //    权限标识
    @NotBlank(message = "权限标识不能为空")
    @Size(max = 255, message = "权限标识长度超出范围,长度范围为100位之内")
    @Column(name = "identification")
    private String identification;
    //    权限系统分类
    @NotBlank(message = "权限系统分类不能为空")
    @Size(max = 255, message = "权限系统分类长度超出范围,长度范围为100位之内")
    @Column(name = "sysType")
    private String sysType = "user";
    //    权限父级
    @NotBlank(message = "权限所属父级不能为空")
    @Size(max = 255, message = "权限所属父级长度超出范围,长度范围为100位之内")
    @Column(name = "parents")
    private String parents = "0";
    //    权限类型  1：左侧菜单 2：子页面顶部菜单 3：子页面内部按钮
    @Range(min = 0, max = 4, message = "不是正确的类型")
    @Column(name = "jur_type")
    private long jurType = 1L;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getSysType() {
        return sysType;
    }

    public void setSysType(String sysType) {
        this.sysType = sysType;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public long getJurType() {
        return jurType;
    }

    public void setJurType(long jurType) {
        this.jurType = jurType;
    }

    public JurisdictionModel() {
        super();
    }

    public JurisdictionModel(String name, String identification) {
        this.name = name;
        this.identification = identification;
    }

    public JurisdictionModel(String uuid, String name, String identification, String sysType, String parents, long jurType) {
        this.uuid = uuid;
        this.name = name;
        this.identification = identification;
        this.sysType = sysType;
        this.parents = parents;
        this.jurType = jurType;
    }

    @Override
    public String toString() {
        return "JurisdictionModel{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", identification='" + identification + '\'' +
                ", sysType='" + sysType + '\'' +
                ", parents='" + parents + '\'' +
                ", jurType=" + jurType +
                '}';
    }
}
