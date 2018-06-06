package com.zlj.zl.user.applyPeoTypes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@Entity(name = "apply_peo_types_table")
@Table(comment = "申请人类型维护", appliesTo = "apply_peo_types_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApplyPeoTypesModel {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //类型名称
    @Column(name = "types_name")
    private String typesName;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getTypesName() {
        return typesName;
    }

    public void setTypesName(String typesName) {
        this.typesName = typesName;
    }

    public ApplyPeoTypesModel() {
        super();
    }

    public ApplyPeoTypesModel(String uuid, String typesName) {
        this.uuid = uuid;
        this.typesName = typesName;
    }

    @Override
    public String toString() {
        return "ApplyPeoTypesModel{" +
                "uuid='" + uuid + '\'' +
                ", typesName='" + typesName + '\'' +
                '}';
    }
}
