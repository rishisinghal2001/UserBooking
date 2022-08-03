package com.rishi.userbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name="userdetail")
@Table(name="userdetail")
@Where(clause = "is_deleted = 'false'")
public class UserEntity extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4753089813246159260L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer userId ; 
	   private String firstName; 
	   private String lastName;
	   private String gender ;
	   private String email ;
	   private String userpassword; 
	   private boolean isDeleted;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	
}
