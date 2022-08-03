package com.rishi.userbooking.DTO;

import java.io.Serializable;
import java.sql.Timestamp;

public class BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8924303018176745642L;
	private Timestamp addDate = new Timestamp(System.currentTimeMillis());
	private String addedBy;
	private Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
	private String LastModifiedBy;
	
	public Timestamp getAddDate() {
		return addDate;
	}
	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getLastModifiedBy() {
		return LastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		LastModifiedBy = lastModifiedBy;
	}
	
	
}
