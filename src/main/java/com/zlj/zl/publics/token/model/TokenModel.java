package com.zlj.zl.publics.token.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "token_table")
@Table(comment = "令牌表", appliesTo = "token_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenModel implements Serializable {
    //必须是这个包javax.persistence.*;
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;

    @Column(name = "account")
    private String account;

    @Column(name = "token")
    private String token;

    //    过期时间
    @Column(name = "end_time")
    private long endTimes;

    //    是否已经使用，token只能使用一次
    @Column(name = "is_use")
    private String isUse;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getEndTimes() {
        return endTimes;
    }

    public void setEndTimes(long endTimes) {
        this.endTimes = endTimes;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public TokenModel() {
        super();
    }

    public TokenModel(String uuid, String account, String token, long endTimes, String isUse) {
        this.uuid = uuid;
        this.account = account;
        this.token = token;
        this.endTimes = endTimes;
        this.isUse = isUse;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "uuid='" + uuid + '\'' +
                ", account='" + account + '\'' +
                ", token='" + token + '\'' +
                ", endTimes=" + endTimes +
                ", isUse='" + isUse + '\'' +
                '}';
    }
}
