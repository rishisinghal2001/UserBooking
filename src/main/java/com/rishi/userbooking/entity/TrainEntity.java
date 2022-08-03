package com.rishi.userbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name = "traindetail")
@Table(name = "traindetail")
@Where(clause = "is_deleted = 'false'")
public class TrainEntity extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer trainDetailId;
	private Integer trainNo;
	private String startingStation;
	private String lastStation;
	private java.sql.Date departureDate;
	private java.sql.Date arrivalDate;
	private boolean isDeleted;
	public Integer getTrainDetailId() {
		return trainDetailId;
	}
	public void setTrainDetailId(Integer trainDetailId) {
		this.trainDetailId = trainDetailId;
	}
	public Integer getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(Integer trainNo) {
		this.trainNo = trainNo;
	}
	public String getStartingStation() {
		return startingStation;
	}
	public void setStartingStation(String startingStation) {
		this.startingStation = startingStation;
	}
	public String getLastStation() {
		return lastStation;
	}
	public void setLastStation(String lastStation) {
		this.lastStation = lastStation;
	}
	public java.sql.Date getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(java.sql.Date departureDate) {
		this.departureDate = departureDate;
	}
	public java.sql.Date getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(java.sql.Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
