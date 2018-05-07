package com.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class FlowUser implements Serializable {
    private Integer ullId;

    private Integer userId;

    private String token;

    private String operatingType;

    private Date operatingDate;

    private String createdBy;

    private Date creationDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private String bz;

    private static final long serialVersionUID = 1L;

    public Integer getUllId() {
        return ullId;
    }

    public void setUllId(Integer ullId) {
        this.ullId = ullId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public String getOperatingType() {
        return operatingType;
    }

    public void setOperatingType(String operatingType) {
        this.operatingType = operatingType == null ? null : operatingType.trim();
    }

    public Date getOperatingDate() {
        return operatingDate;
    }

    public void setOperatingDate(Date operatingDate) {
        this.operatingDate = operatingDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy == null ? null : lastUpdateBy.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}