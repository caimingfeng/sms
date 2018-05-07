package com.sms.entity;

import java.io.Serializable;
import java.util.Date;

public class CourseMessage implements Serializable {
    private Integer coursesMessageId;

    private String teachingClass;

    private String type;

    private Integer week;

    private Integer weekNum;

    private String section;

    private String address;

    private String content;

    private Integer limitSelect;

    private String createdBy;

    private Date creationDate;

    private String lastUpdateBy;

    private Date lastUpdateDate;

    private String bz;
    
    private String cno;
    
    private int teaId;
    
    private Course course;
    
    private String teaName;
    
    private String parentId;
    
    private int scoreType;
    
    private double regularPer;
    
    private double examPer;
    
    private String editScore;

    private static final long serialVersionUID = 1L;

    public Integer getCoursesMessageId() {
        return coursesMessageId;
    }

    public void setCoursesMessageId(Integer coursesMessageId) {
        this.coursesMessageId = coursesMessageId;
    }

    public String getTeachingClass() {
        return teachingClass;
    }

    public void setTeachingClass(String teachingClass) {
        this.teachingClass = teachingClass;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public Integer getWeekNum() {
        return weekNum;
    }

    public void setWeekNum(Integer weekNum) {
        this.weekNum = weekNum;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section == null ? null : section.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Integer getLimitSelect() {
        return limitSelect;
    }

    public void setLimitSelect(Integer limitSelect) {
        this.limitSelect = limitSelect;
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

	public String getCno() {
		return cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public int getTeaId() {
		return teaId;
	}

	public void setTeaId(int teaId) {
		this.teaId = teaId;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getTeaName() {
		return teaName;
	}

	public void setTeaName(String teaName) {
		this.teaName = teaName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getScoreType() {
		return scoreType;
	}

	public void setScoreType(int scoreType) {
		this.scoreType = scoreType;
	}

	public double getRegularPer() {
		return regularPer;
	}

	public void setRegularPer(double regularPer) {
		this.regularPer = regularPer;
	}

	public double getExamPer() {
		return examPer;
	}

	public void setExamPer(double examPer) {
		this.examPer = examPer;
	}

	public String getEditScore() {
		return editScore;
	}

	public void setEditScore(String editScore) {
		this.editScore = editScore;
	}
}