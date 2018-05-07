package com.sms.entity;

import java.io.Serializable;

public class MajorTeam implements Serializable {
    private Integer majorClassId;

    private Integer majorId;

    private Integer classId;
    
    private String headerMonitor;

    private static final long serialVersionUID = 1L;

    public Integer getMajorClassId() {
        return majorClassId;
    }

    public void setMajorClassId(Integer majorClassId) {
        this.majorClassId = majorClassId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

	public String getHeaderMonitor() {
		return headerMonitor;
	}

	public void setHeaderMonitor(String headerMonitor) {
		this.headerMonitor = headerMonitor;
	}
}