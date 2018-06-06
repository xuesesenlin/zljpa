package com.zlj.zl.user.patentDataLocking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author ld
 * @name
 * @table
 * @remarks
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatentDataLockingModel implements Serializable {

    private long impDate;

    private String lx;

    public long getImpDate() {
        return impDate;
    }

    public void setImpDate(long impDate) {
        this.impDate = impDate;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public PatentDataLockingModel() {
        super();
    }

    public PatentDataLockingModel(long impDate, String lx) {
        this.impDate = impDate;
        this.lx = lx;
    }

    @Override
    public String toString() {
        return "PatentDataLockingModel{" +
                "impDate='" + impDate + '\'' +
                ", lx='" + lx + '\'' +
                '}';
    }
}
