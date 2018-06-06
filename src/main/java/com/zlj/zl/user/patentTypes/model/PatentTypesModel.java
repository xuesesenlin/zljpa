package com.zlj.zl.user.patentTypes.model;

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
@Entity(name = "patent_types_table")
@Table(comment = "专利类型维护", appliesTo = "patent_types_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatentTypesModel {

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

    public PatentTypesModel() {
        super();
    }

    public PatentTypesModel(String uuid, String typesName) {
        this.uuid = uuid;
        this.typesName = typesName;
    }

    @Override
    public String toString() {
        return "PatentTypesModel{" +
                "uuid='" + uuid + '\'' +
                ", typesName='" + typesName + '\'' +
                '}';
    }
}
