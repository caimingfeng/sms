package com.sms.util.upload;

import java.io.Serializable;

public class ImageResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private String status;
	private String img_w;
	private String img_h;
	private String file_id;
	private String file_name;
	private String m_file;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImg_w() {
		return img_w;
	}

	public void setImg_w(String img_w) {
		this.img_w = img_w;
	}

	public String getImg_h() {
		return img_h;
	}

	public void setImg_h(String img_h) {
		this.img_h = img_h;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getM_file() {
		return m_file;
	}

	public void setM_file(String m_file) {
		this.m_file = m_file;
	}

}
