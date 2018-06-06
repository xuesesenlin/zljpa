package com.zlj.zl.publics.logger.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author ld
 * @name 日志
 * @table
 * @remarks
 */
@Entity(name = "logger_table")
@Table(comment = "日志", appliesTo = "logger_table")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoggerModel {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String uuid;
    //    级别
    @Column(name = "level")
    private String level;

    @Column(name = "logger", length = 4000)
    private String logger;

    @Column(name = "thread", length = 4000)
    private String thread;

    @Column(name = "message", length = 4000)
    private String message;

    @Column(name = "systime")
    private long systime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLogger() {
        return logger;
    }

    public void setLogger(String logger) {
        this.logger = logger;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getSystime() {
        return systime;
    }

    public void setSystime(long systime) {
        this.systime = systime;
    }

    public LoggerModel() {
        super();
    }

    public LoggerModel(String uuid, String level, String logger, String thread, String message, long systime) {
        this.uuid = uuid;
        this.level = level;
        this.logger = logger;
        this.thread = thread;
        this.message = message;
        this.systime = systime;
    }

    @Override
    public String toString() {
        return "LoggerModel{" +
                "uuid='" + uuid + '\'' +
                ", level='" + level + '\'' +
                ", logger='" + logger + '\'' +
                ", thread='" + thread + '\'' +
                ", message='" + message + '\'' +
                ", systime=" + systime +
                '}';
    }
}
