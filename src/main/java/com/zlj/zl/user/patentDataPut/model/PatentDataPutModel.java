package com.zlj.zl.user.patentDataPut.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author ld
 * @name 专利数据维护
 * @table patent_data_put_table
 * @remarks
 */
@Entity(name = "patent_data_put_table")
@Table(comment = "专利数据维护", appliesTo = "patent_data_put_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatentDataPutModel implements Serializable {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //申请号
    @Column(name = "apply_code")
    private String applyCode;
    //    授权入库日
    @Column(name = "empowerment_date")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private long empowermentDate;
    //    申请日
    @Column(name = "apply_date")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private long applyDate;
    //    发明名称
    @Column(name = "invention_name")
    private String inventionName;
    //    代理机构代码
    @Column(name = "agent_code")
    private String agentCode;
    //    代理机构名称
    @Column(name = "agent_name")
    private String agentName;
    //    主分类号
    @Column(name = "main_types")
    private String mainTypes;
    //    专利人名称
    @Column(name = "patent_peo_name")
    private String patentPeoName;
    //    专利人邮编
    @Column(name = "zip_code")
    private String zipCode;
    //    专利人地址
    @Column(name = "peo_address")
    private String peoAddress;
    //    案卷入库日
    @Column(name = "file_enter_date")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private long fileEnterDate;
    //    专利类型
    @Column(name = "patent_types")
    private String patentTypes;
    //    专利人类型
    @Column(name = "app_peo_types")
    private String appPeoTypes;
    //    省份
    @Column(name = "province")
    private String province;
    //    城市
    @Column(name = "city")
    private String city;
    //    区/县
    @Column(name = "area")
    private String area;
    //    申请方式
    @Column(name = "apply_mode")
    private String applyMode;
    //    导入日期
    @Column(name = "imp_date")
//    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private long impDate;
    //    申请1,授权2,有效3 类型
    @Column(name = "apply_authorization")
    private String applyAuthorization;
    //    0:正常记录 -1:被删除的记录
    @Column(name = "flag")
    private String flag = "0";
    //    是否锁定
    @Column(name = "locking")
    private String locking = "N";
    //    是否标记为消零
    @Column(name = "elimination_zero")
    private String eliminationZero = "N";
    //    企业类型
    @Column(name = "company_type")
    private String companyType;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public long getEmpowermentDate() {
        return empowermentDate;
    }

    public void setEmpowermentDate(long empowermentDate) {
        this.empowermentDate = empowermentDate;
    }

    public long getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(long applyDate) {
        this.applyDate = applyDate;
    }

    public String getInventionName() {
        return inventionName;
    }

    public void setInventionName(String inventionName) {
        this.inventionName = inventionName;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    public String getMainTypes() {
        return mainTypes;
    }

    public void setMainTypes(String mainTypes) {
        this.mainTypes = mainTypes;
    }

    public String getPatentPeoName() {
        return patentPeoName;
    }

    public void setPatentPeoName(String patentPeoName) {
        this.patentPeoName = patentPeoName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getPeoAddress() {
        return peoAddress;
    }

    public void setPeoAddress(String peoAddress) {
        this.peoAddress = peoAddress;
    }

    public long getFileEnterDate() {
        return fileEnterDate;
    }

    public void setFileEnterDate(long fileEnterDate) {
        this.fileEnterDate = fileEnterDate;
    }

    public String getPatentTypes() {
        return patentTypes;
    }

    public void setPatentTypes(String patentTypes) {
        this.patentTypes = patentTypes;
    }

    public String getAppPeoTypes() {
        return appPeoTypes;
    }

    public void setAppPeoTypes(String appPeoTypes) {
        this.appPeoTypes = appPeoTypes;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getApplyMode() {
        return applyMode;
    }

    public void setApplyMode(String applyMode) {
        this.applyMode = applyMode;
    }

    public long getImpDate() {
        return impDate;
    }

    public void setImpDate(long impDate) {
        this.impDate = impDate;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getApplyAuthorization() {
        return applyAuthorization;
    }

    public void setApplyAuthorization(String applyAuthorization) {
        this.applyAuthorization = applyAuthorization;
    }

    public String getLocking() {
        return locking;
    }

    public void setLocking(String locking) {
        this.locking = locking;
    }

    public String getEliminationZero() {
        return eliminationZero;
    }

    public void setEliminationZero(String eliminationZero) {
        this.eliminationZero = eliminationZero;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public PatentDataPutModel() {
        super();
    }

    public PatentDataPutModel(String uuid, String applyCode, long empowermentDate, long applyDate, String inventionName, String agentCode, String agentName, String mainTypes, String patentPeoName, String zipCode, String peoAddress, long fileEnterDate, String patentTypes, String appPeoTypes, String province, String city, String area, String applyMode, long impDate, String applyAuthorization, String flag, String locking, String eliminationZero) {
        this.uuid = uuid;
        this.applyCode = applyCode;
        this.empowermentDate = empowermentDate;
        this.applyDate = applyDate;
        this.inventionName = inventionName;
        this.agentCode = agentCode;
        this.agentName = agentName;
        this.mainTypes = mainTypes;
        this.patentPeoName = patentPeoName;
        this.zipCode = zipCode;
        this.peoAddress = peoAddress;
        this.fileEnterDate = fileEnterDate;
        this.patentTypes = patentTypes;
        this.appPeoTypes = appPeoTypes;
        this.province = province;
        this.city = city;
        this.area = area;
        this.applyMode = applyMode;
        this.impDate = impDate;
        this.applyAuthorization = applyAuthorization;
        this.flag = flag;
        this.locking = locking;
        this.eliminationZero = eliminationZero;
    }

    public PatentDataPutModel(String uuid, String applyCode, long empowermentDate, long applyDate, String inventionName, String agentCode, String agentName, String mainTypes, String patentPeoName, String zipCode, String peoAddress, long fileEnterDate, String patentTypes, String appPeoTypes, String province, String city, String area, String applyMode, long impDate, String applyAuthorization, String flag, String locking, String eliminationZero, String companyType) {
        this.uuid = uuid;
        this.applyCode = applyCode;
        this.empowermentDate = empowermentDate;
        this.applyDate = applyDate;
        this.inventionName = inventionName;
        this.agentCode = agentCode;
        this.agentName = agentName;
        this.mainTypes = mainTypes;
        this.patentPeoName = patentPeoName;
        this.zipCode = zipCode;
        this.peoAddress = peoAddress;
        this.fileEnterDate = fileEnterDate;
        this.patentTypes = patentTypes;
        this.appPeoTypes = appPeoTypes;
        this.province = province;
        this.city = city;
        this.area = area;
        this.applyMode = applyMode;
        this.impDate = impDate;
        this.applyAuthorization = applyAuthorization;
        this.flag = flag;
        this.locking = locking;
        this.eliminationZero = eliminationZero;
        this.companyType = companyType;
    }

    @Override
    public String toString() {
        return "PatentDataPutModel{" +
                "uuid='" + uuid + '\'' +
                ", applyCode='" + applyCode + '\'' +
                ", empowermentDate=" + empowermentDate +
                ", applyDate=" + applyDate +
                ", inventionName='" + inventionName + '\'' +
                ", agentCode='" + agentCode + '\'' +
                ", agentName='" + agentName + '\'' +
                ", mainTypes='" + mainTypes + '\'' +
                ", patentPeoName='" + patentPeoName + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", peoAddress='" + peoAddress + '\'' +
                ", fileEnterDate=" + fileEnterDate +
                ", patentTypes='" + patentTypes + '\'' +
                ", appPeoTypes='" + appPeoTypes + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", applyMode='" + applyMode + '\'' +
                ", impDate=" + impDate +
                ", applyAuthorization='" + applyAuthorization + '\'' +
                ", flag='" + flag + '\'' +
                ", locking='" + locking + '\'' +
                ", eliminationZero='" + eliminationZero + '\'' +
                ", companyType='" + companyType + '\'' +
                '}';
    }
}
