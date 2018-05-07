package com.sms.util.upload;

import java.io.Serializable;

public class ImageRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private String entity_source;
	private String user_id;
	private String folder_id;
	private String file;
	private String name;

	public String getEntity_source() {
		return entity_source;
	}

	public void setEntity_source(String entity_source) {
		this.entity_source = entity_source;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getFolder_id() {
		return folder_id;
	}

	public void setFolder_id(String folder_id) {
		this.folder_id = folder_id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
