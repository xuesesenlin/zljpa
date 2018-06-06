package com.zlj.zl.user.patentDataPut.model;

/**
 * @author ld
 * @name 专利数据维护查询条件
 * @table 无表
 * @remarks
 */
public class PatentDataPutQueryModel {

    private String sqsqzt;
    private String drnyStar;
    private String drnyEnd;
    private String zlsqh;
    private String zflh;
    private String zllx;
    private String sqrqStar;
    private String sqrqEnd;
    private String sqrdz;
    private String sqrlx;
    private String szss;
    private String szsc;
    private String szsx;
    private String ajrkrStar;
    private String ajrkrEnd;
    //    企业类型
    private String qylx;
    //    是否标记为消零数据
    private String sfxl;

    public String getSqsqzt() {
        return sqsqzt;
    }

    public void setSqsqzt(String sqsqzt) {
        this.sqsqzt = sqsqzt;
    }

    public String getDrnyStar() {
        return drnyStar;
    }

    public void setDrnyStar(String drnyStar) {
        this.drnyStar = drnyStar;
    }

    public String getDrnyEnd() {
        return drnyEnd;
    }

    public void setDrnyEnd(String drnyEnd) {
        this.drnyEnd = drnyEnd;
    }

    public String getZlsqh() {
        return zlsqh;
    }

    public void setZlsqh(String zlsqh) {
        this.zlsqh = zlsqh;
    }

    public String getZflh() {
        return zflh;
    }

    public void setZflh(String zflh) {
        this.zflh = zflh;
    }

    public String getZllx() {
        return zllx;
    }

    public void setZllx(String zllx) {
        this.zllx = zllx;
    }

    public String getSqrqStar() {
        return sqrqStar;
    }

    public void setSqrqStar(String sqrqStar) {
        this.sqrqStar = sqrqStar;
    }

    public String getSqrqEnd() {
        return sqrqEnd;
    }

    public void setSqrqEnd(String sqrqEnd) {
        this.sqrqEnd = sqrqEnd;
    }

    public String getSqrdz() {
        return sqrdz;
    }

    public void setSqrdz(String sqrdz) {
        this.sqrdz = sqrdz;
    }

    public String getSqrlx() {
        return sqrlx;
    }

    public void setSqrlx(String sqrlx) {
        this.sqrlx = sqrlx;
    }

    public String getSzss() {
        return szss;
    }

    public void setSzss(String szss) {
        this.szss = szss;
    }

    public String getSzsc() {
        return szsc;
    }

    public void setSzsc(String szsc) {
        this.szsc = szsc;
    }

    public String getSzsx() {
        return szsx;
    }

    public void setSzsx(String szsx) {
        this.szsx = szsx;
    }

    public String getAjrkrStar() {
        return ajrkrStar;
    }

    public void setAjrkrStar(String ajrkrStar) {
        this.ajrkrStar = ajrkrStar;
    }

    public String getAjrkrEnd() {
        return ajrkrEnd;
    }

    public void setAjrkrEnd(String ajrkrEnd) {
        this.ajrkrEnd = ajrkrEnd;
    }

    public String getQylx() {
        return qylx;
    }

    public void setQylx(String qylx) {
        this.qylx = qylx;
    }

    public String getSfxl() {
        return sfxl;
    }

    public void setSfxl(String sfxl) {
        this.sfxl = sfxl;
    }

    public PatentDataPutQueryModel() {
        super();
    }

    public PatentDataPutQueryModel(String sqsqzt, String drnyStar, String drnyEnd, String zlsqh, String zflh, String zllx, String sqrqStar, String sqrqEnd, String sqrdz, String sqrlx, String szss, String szsc, String szsx, String ajrkrStar, String ajrkrEnd, String qylx, String sfxl) {
        this.sqsqzt = sqsqzt;
        this.drnyStar = drnyStar;
        this.drnyEnd = drnyEnd;
        this.zlsqh = zlsqh;
        this.zflh = zflh;
        this.zllx = zllx;
        this.sqrqStar = sqrqStar;
        this.sqrqEnd = sqrqEnd;
        this.sqrdz = sqrdz;
        this.sqrlx = sqrlx;
        this.szss = szss;
        this.szsc = szsc;
        this.szsx = szsx;
        this.ajrkrStar = ajrkrStar;
        this.ajrkrEnd = ajrkrEnd;
        this.qylx = qylx;
        this.sfxl = sfxl;
    }

    @Override
    public String toString() {
        return "PatentDataPutQueryModel{" +
                "sqsqzt='" + sqsqzt + '\'' +
                ", drnyStar='" + drnyStar + '\'' +
                ", drnyEnd='" + drnyEnd + '\'' +
                ", zlsqh='" + zlsqh + '\'' +
                ", zflh='" + zflh + '\'' +
                ", zllx='" + zllx + '\'' +
                ", sqrqStar='" + sqrqStar + '\'' +
                ", sqrqEnd='" + sqrqEnd + '\'' +
                ", sqrdz='" + sqrdz + '\'' +
                ", sqrlx='" + sqrlx + '\'' +
                ", szss='" + szss + '\'' +
                ", szsc='" + szsc + '\'' +
                ", szsx='" + szsx + '\'' +
                ", ajrkrStar='" + ajrkrStar + '\'' +
                ", ajrkrEnd='" + ajrkrEnd + '\'' +
                ", qylx='" + qylx + '\'' +
                ", sfxl='" + sfxl + '\'' +
                '}';
    }
}
