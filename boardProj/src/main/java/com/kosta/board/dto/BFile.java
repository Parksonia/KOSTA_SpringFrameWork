package com.kosta.board.dto;

import java.sql.Date;

public class BFile {

	private Integer num;
	private String directory;
	private String name;
	private Long size;
	private String contenttype;
	private Date uploaddate;
	
	public BFile() {
		// TODO Auto-generated constructor stub
	}
	
	public BFile(Integer num, String directory, String name, Long size, String contenttype, Date uploaddate) {
		super();
		this.num = num;
		this.directory = directory;
		this.name = name;
		this.size = size;
		this.contenttype = contenttype;
		this.uploaddate = uploaddate;
	
	
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}

	public Date getUploaddate() {
		return uploaddate;
	}

	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}

	@Override
	public String toString() {
		return "BFile [num=" + num + ", directory=" + directory + ", name=" + name + ", size=" + size + ", contenttype="
				+ contenttype + ", uploaddate=" + uploaddate + "]";
	}
	
	
}
