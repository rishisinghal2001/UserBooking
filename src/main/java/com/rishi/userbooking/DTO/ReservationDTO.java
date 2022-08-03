package com.rishi.userbooking.DTO;

public class ReservationDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3480894517574872485L;
	   private int reservationId; 
	   private long pnrNo ;
	   private int trainNo; 
	   private String startingStation;
	   private String lastStation;
	   private java.sql.Date departureDate;
	   private java.sql.Date arrivalDate;
	   private boolean currentStatus;
	   private boolean isDeleted	;
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	
	public long getPnrNo() {
		return pnrNo;
	}
	public void setPnrNo(long pnrNo) {
		this.pnrNo = pnrNo;
	}
	public int getTrainNo() {
		return trainNo;
	}
	public void setTrainNo(int trainNo) {
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
