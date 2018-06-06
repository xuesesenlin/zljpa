package com.zlj.zl.user.companyTypes.model;

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
 * @name 企业类型
 * @table company_types_table
 * @remarks
 */
@Entity(name = "company_types_table")
@Table(comment = "企业类型", appliesTo = "company_types_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompanyTypesModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "类型不能为空")
    @Size(max = 100, message = "类型长度超出范围,长度范围为100位之内")
    @Column(name = "company_types")
    private String companyTypes;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCompanyTypes() {
        return companyTypes;
    }

    public void setCompanyTypes(String companyTypes) {
        this.companyTypes = companyTypes;
    }

    public CompanyTypesModel() {
        super();
    }

    public CompanyTypesModel(String uuid, String companyTypes) {
        this.uuid = uuid;
        this.companyTypes = companyTypes;
    }

    @Override
    public String toString() {
        return "CompanyTypesModel{" +
                "uuid='" + uuid + '\'' +
                ", companyTypes='" + companyTypes + '\'' +
                '}';
    }
}
