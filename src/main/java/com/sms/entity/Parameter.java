package com.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class Parameter implements Serializable {
    private Integer parId;

    private String paramCd;

    private String paramValue;

    private String description;

    private String createdBy;

    private Date creationDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private String bz;

    private Integer stsCd;

    private static final long serialVersionUID = 1L;

    public Integer getParId() {
        return parId;
    }

    public void setParId(Integer parId) {
        this.parId = parId;
    }

    public String getParamCd() {
        return paramCd;
    }

    public void setParamCd(String paramCd) {
        this.paramCd = paramCd == null ? null : paramCd.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    public Integer getStsCd() {
        return stsCd;
    }

    public void setStsCd(Integer stsCd) {
        this.stsCd = stsCd;
    }
}