package com.zlj.zl.user.eliminateZero.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
public class EliminateQueryModel implements Serializable {

    //    申请授权状况
    private String applyAuthorization;
    //    导入年月起始
    private Date importStart;
    //    导入年月截至
    private Date importEnd;
    //    申请专利号
    private String patentNumber;
    //    主分类号
    private String mainClassification;
    //    专利类型
    private String patentTypes;
    //    申请日期开始
    private Date applyDateStart;
    //    申请日期结束
    private Date applyDateEnd;
    //    申请人地址
    private String applyAddress;
    //    申请人类型
    private String applyTypes;
    //    所在城市
    private String citys;
    //    所在区、县
    private String countys;
    //    案卷入库日开始
    private Date warehousingDateStart;
    //    案卷入库日结束
    private Date warehousingDateEnd;
    //    显示消零数据
    private String displays;

    public String getApplyAuthorization() {
        return applyAuthorization;
    }

    public void setApplyAuthorization(String applyAuthorization) {
        this.applyAuthorization = applyAuthorization;
    }

    public Date getImportStart() {
        return importStart;
    }

    public void setImportStart(Date importStart) {
        this.importStart = importStart;
    }

    public Date getImportEnd() {
        return importEnd;
    }

    public void setImportEnd(Date importEnd) {
        this.importEnd = importEnd;
    }

    public String getPatentNumber() {
        return patentNumber;
    }

    public void setPatentNumber(String patentNumber) {
        this.patentNumber = patentNumber;
    }

    public String getMainClassification() {
        return mainClassification;
    }

    public void setMainClassification(String mainClassification) {
        this.mainClassification = mainClassification;
    }

    public String getPatentTypes() {
        return patentTypes;
    }

    public void setPatentTypes(String patentTypes) {
        this.patentTypes = patentTypes;
    }

    public Date getApplyDateStart() {
        return applyDateStart;
    }

    public void setApplyDateStart(Date applyDateStart) {
        this.applyDateStart = applyDateStart;
    }

    public Date getApplyDateEnd() {
        return applyDateEnd;
    }

    public void setApplyDateEnd(Date applyDateEnd) {
        this.applyDateEnd = applyDateEnd;
    }

    public String getApplyAddress() {
        return applyAddress;
    }

    public void setApplyAddress(String applyAddress) {
        this.applyAddress = applyAddress;
    }

    public String getApplyTypes() {
        return applyTypes;
    }

    public void setApplyTypes(String applyTypes) {
        this.applyTypes = applyTypes;
    }

    public String getCitys() {
        return citys;
    }

    public void setCitys(String citys) {
        this.citys = citys;
    }

    public String getCountys() {
        return countys;
    }

    public void setCountys(String countys) {
        this.countys = countys;
    }

    public Date getWarehousingDateStart() {
        return warehousingDateStart;
    }

    public void setWarehousingDateStart(Date warehousingDateStart) {
        this.warehousingDateStart = warehousingDateStart;
    }

    public Date getWarehousingDateEnd() {
        return warehousingDateEnd;
    }

    public void setWarehousingDateEnd(Date warehousingDateEnd) {
        this.warehousingDateEnd = warehousingDateEnd;
    }

    public String getDisplays() {
        return displays;
    }

    public void setDisplays(String displays) {
        this.displays = displays;
    }

    public EliminateQueryModel() {
        super();
    }

    public EliminateQueryModel(String applyAuthorization, Date importStart, Date importEnd, String patentNumber, String mainClassification, String patentTypes, Date applyDateStart, Date applyDateEnd, String applyAddress, String applyTypes, String citys, String countys, Date warehousingDateStart, Date warehousingDateEnd, String displays) {
        this.applyAuthorization = applyAuthorization;
        this.importStart = importStart;
        this.importEnd = importEnd;
        this.patentNumber = patentNumber;
        this.mainClassification = mainClassification;
        this.patentTypes = patentTypes;
        this.applyDateStart = applyDateStart;
        this.applyDateEnd = applyDateEnd;
        this.applyAddress = applyAddress;
        this.applyTypes = applyTypes;
        this.citys = citys;
        this.countys = countys;
        this.warehousingDateStart = warehousingDateStart;
        this.warehousingDateEnd = warehousingDateEnd;
        this.displays = displays;
    }

    @Override
    public String toString() {
        return "EliminateQueryModel{" +
                "applyAuthorization='" + applyAuthorization + '\'' +
                ", importStart=" + importStart +
                ", importEnd=" + importEnd +
                ", patentNumber='" + patentNumber + '\'' +
                ", mainClassification='" + mainClassification + '\'' +
                ", patentTypes='" + patentTypes + '\'' +
                ", applyDateStart=" + applyDateStart +
                ", applyDateEnd=" + applyDateEnd +
                ", applyAddress='" + applyAddress + '\'' +
                ", applyTypes='" + applyTypes + '\'' +
                ", citys='" + citys + '\'' +
                ", countys='" + countys + '\'' +
                ", warehousingDateStart=" + warehousingDateStart +
                ", warehousingDateEnd=" + warehousingDateEnd +
                ", displays='" + displays + '\'' +
                '}';
    }
}
