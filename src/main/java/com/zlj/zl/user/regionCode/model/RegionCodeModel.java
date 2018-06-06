package com.zlj.zl.user.regionCode.model;

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
 * @name 地域代码维护
 * @table region_code_table
 * @remarks
 */
@Entity(name = "region_code_table")
@Table(comment = "地域代码维护", appliesTo = "region_code_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegionCodeModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "区/县不能为空")
    @Size(max = 100, message = "区/县长度超出范围,长度范围为100位之内")
    @Column(name = "area")
    private String area;

    @NotBlank(message = "关键字不能为空")
    @Size(max = 50000, message = "关键字长度超出范围,长度范围为50000位之内")
    @Column(name = "keyworda", columnDefinition = "text")
    private String keyworda;

    @NotBlank(message = "关键字不能为空")
    @Size(max = 50000, message = "关键字长度超出范围,长度范围为50000位之内")
    @Column(name = "keywordb", columnDefinition = "text")
    private String keywordb;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getKeyworda() {
        return keyworda;
    }

    public void setKeyworda(String keyworda) {
        this.keyworda = keyworda;
    }

    public String getKeywordb() {
        return keywordb;
    }

    public void setKeywordb(String keywordb) {
        this.keywordb = keywordb;
    }

    public RegionCodeModel() {
        super();
    }

    public RegionCodeModel(String uuid, String area, String keyworda, String keywordb) {
        this.uuid = uuid;
        this.area = area;
        this.keyworda = keyworda;
        this.keywordb = keywordb;
    }

    @Override
    public String toString() {
        return "RegionCodeModel{" +
                "uuid='" + uuid + '\'' +
                ", area='" + area + '\'' +
                ", keyworda='" + keyworda + '\'' +
                ", keywordb='" + keywordb + '\'' +
                '}';
    }
}
