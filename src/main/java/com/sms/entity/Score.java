package com.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class Score implements Serializable {
    private Integer gradeId;

    private String sno;

    private String cno;

    private Integer coursesMessageId;

    private String regularGrade;

    private String examGrade;

    private String finalGrade;

    private Double gpa;

    private String term;

    private Integer state;

    private Integer type;

    private String bz;

    private String createdBy;

    private Date creationDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private static final long serialVersionUID = 1L;

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno == null ? null : sno.trim();
    }

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno == null ? null : cno.trim();
    }

    public Integer getCoursesMessageId() {
        return coursesMessageId;
    }

    public void setCoursesMessageId(Integer coursesMessageId) {
        this.coursesMessageId = coursesMessageId;
    }

    public String getRegularGrade() {
        return regularGrade;
    }

    public void setRegularGrade(String regularGrade) {
        this.regularGrade = regularGrade == null ? null : regularGrade.trim();
    }

    public String getExamGrade() {
        return examGrade;
    }

    public void setExamGrade(String examGrade) {
        this.examGrade = examGrade == null ? null : examGrade.trim();
    }

    public String getFinalGrade() {
        return finalGrade;
    }

    public void setFinalGrade(String finalGrade) {
        this.finalGrade = finalGrade == null ? null : finalGrade.trim();
    }

    public Double getGpa() {
        return gpa;
    }

    public void setGpa(Double gpa) {
        this.gpa = gpa;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term == null ? null : term.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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
}