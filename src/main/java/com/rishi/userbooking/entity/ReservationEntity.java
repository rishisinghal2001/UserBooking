package com.rishi.userbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name = "reservationdetail")
@Table(name = "reservationdetail")
@Where(clause = "is_deleted = 'false'")
public class ReservationEntity extends BaseEntity{
	private static final long serialVersionUID = -3480894517574872485L;
	
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Integer reservationId; 
	   private long pnrNo ;
	   private Integer trainNo; 
	   private String startingStation;
	   private String lastStation;
	   private java.sql.Date departureDate;
	   private java.sql.Date arrivalDate;
	   private boolean currentStatus;
	   private boolean isDeleted	;
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public long getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
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
	public boolean isCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(boolean currentStatus) {
		this.currentStatus = currentStatus;
	}
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	   
	   	
}
