package com.zlj.zl.user.keyEnterprises.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Entity(name = "key_enterprises_table")
@Table(comment = "重点企业代码维护", appliesTo = "key_enterprises_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class KeyEnterprisesModel {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "企业类型不能为空")
    @Size(max = 100, message = "企业类型长度超出范围,长度范围为100位之内")
    @Column(name = "qylx")
    private String qylx;

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

    public String getQylx() {
        return qylx;
    }

    public void setQylx(String qylx) {
        this.qylx = qylx;
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

    public KeyEnterprisesModel() {
        super();
    }

    public KeyEnterprisesModel(String uuid, String qylx, String keyworda, String keywordb) {
        this.uuid = uuid;
        this.qylx = qylx;
        this.keyworda = keyworda;
        this.keywordb = keywordb;
    }

    @Override
    public String toString() {
        return "KeyEnterprisesModel{" +
                "uuid='" + uuid + '\'' +
                ", qylx='" + qylx + '\'' +
                ", keyworda='" + keyworda + '\'' +
                ", keywordb='" + keywordb + '\'' +
                '}';
    }
}
