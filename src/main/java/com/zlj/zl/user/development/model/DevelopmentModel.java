package com.zlj.zl.user.development.model;

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
 * @name 开发区（介绍）
 * @table development_table
 * @remarks
 */
@Entity(name = "development_table")
@Table(comment = "开发区（介绍）", appliesTo = "development_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DevelopmentModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "标题不能为空")
    @Size(max = 100, message = "标题长度超出范围,长度范围为100位之内")
    @Column(name = "titles")
    private String titles;

    @NotBlank(message = "内容不能为空")
    @Size(max = 50000, message = "内容长度超出范围,长度范围为50000位之内")
    @Column(name = "bodys", length = 50000, columnDefinition = "TEXT")
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

    public DevelopmentModel() {
        super();
    }

    public DevelopmentModel(String uuid, String titles, String bodys) {
        this.uuid = uuid;
        this.titles = titles;
        this.bodys = bodys;
    }

    @Override
    public String toString() {
        return "DevelopmentModel{" +
                "uuid='" + uuid + '\'' +
                ", titles='" + titles + '\'' +
                ", bodys='" + bodys + '\'' +
                '}';
    }
}
