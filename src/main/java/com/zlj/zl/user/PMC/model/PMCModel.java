package com.zlj.zl.user.PMC.model;

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
 * @name 省市县维护
 * @table
 * @remarks
 */
@Entity(name = "pmc_table")
@Table(comment = "省市县维护", appliesTo = "pmc_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PMCModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @NotBlank(message = "名称不能为空")
    @Size(max = 200, message = "名称长度超出范围,长度范围为200位之内")
    @Column(name = "names")
    private String names;

    /**
     * 1：省；2：市；3：县/区
     **/
    @Range(min = 0, max = 4, message = "不是正确的类型")
    @Column(name = "types")
    private int types;

    @Column(name = "patent_uuid")
    private String patentUuid = "0";

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public int getTypes() {
        return types;
    }

    public void setTypes(int types) {
        this.types = types;
    }

    public String getPatentUuid() {
        return patentUuid;
    }

    public void setPatentUuid(String patentUuid) {
        this.patentUuid = patentUuid;
    }

    public PMCModel() {
        super();
    }

    public PMCModel(String uuid, String names, int types, String patentUuid) {
        this.uuid = uuid;
        this.names = names;
        this.types = types;
        this.patentUuid = patentUuid;
    }

    @Override
    public String toString() {
        return "PMCModel{" +
                "uuid='" + uuid + '\'' +
                ", names='" + names + '\'' +
                ", types=" + types +
                ", patentUuid='" + patentUuid + '\'' +
                '}';
    }
}
