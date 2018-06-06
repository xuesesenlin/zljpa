package com.zlj.zl.user.policyType.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author ld
 * @name 政策法规类别
 * @table policy_type_table
 * @remarks
 */
@Entity(name = "policy_type_table")
@Table(comment = "政策法规类别", appliesTo = "policy_type_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PolicyTypeModel implements Serializable {

    //    uuid
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //    类别名称
    @NotBlank(message = "类别名称不能为空")
    @Size(max = 32, message = "类别名称长度超出范围,长度范围为32位之内")
    @Column(name = "type_name")
    private String typeName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public PolicyTypeModel() {
        super();
    }

    public PolicyTypeModel(String uuid, String typeName) {
        this.uuid = uuid;
        this.typeName = typeName;
    }
}
