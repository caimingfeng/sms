package com.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    private String cno;

    private String courseName;

    private Integer period;

    private Double credit;

    private String introduction;

    private Integer categoryId;

    private String createdBy;

    private Date creationDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private String bz;

    private Integer state;

    private Integer exist;
    
    private Integer open;
    
    private CourseCategory category;

    private static final long serialVersionUID = 1L;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getExist() {
        return exist;
    }

    public void setExist(Integer exist) {
        this.exist = exist;
    }

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public CourseCategory getCategory() {
		return category;
	}

	public void setCategory(CourseCategory category) {
		this.category = category;
	}
}