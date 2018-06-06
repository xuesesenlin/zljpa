package com.zlj.zl.user.patentServiceAgency.model;

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
 * @name 专利服务机构
 * @table patent_service_agency_table
 * @remarks
 */
@Entity(name = "patent_service_agency_table")
@Table(comment = "专利服务机构", appliesTo = "patent_service_agency_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatentServiceAgencyModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题长度超出范围,长度范围为100位之内")
    @Column(name = "titles")
    private String titles;

    @NotBlank(message = "内容不能为空")
    @Size(max = 3000, message = "内容长度超出范围,长度范围为3000位之内")
    @Column(name = "bodys", length = 3000, columnDefinition = "TEXT")
    private String bodys;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getBodys() {
        return bodys;
    }

    public void setBodys(String bodys) {
        this.bodys = bodys;
    }

    public PatentServiceAgencyModel() {
        super();
    }

    public PatentServiceAgencyModel(String uuid, String titles, String bodys) {
        this.uuid = uuid;
        this.titles = titles;
        this.bodys = bodys;
    }

    @Override
    public String toString() {
        return "PatentServiceAgencyModel{" +
                "uuid='" + uuid + '\'' +
                ", titles='" + titles + '\'' +
                ", bodys='" + bodys + '\'' +
                '}';
    }
}
