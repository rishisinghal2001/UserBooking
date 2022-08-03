package com.rishi.userbooking.DTO;

public class TrainDTO  extends BaseDTO implements ProcessableResource{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int trainDetailId;
	private int trainNo;
	private String startingStation;
	private String lastStation;
	private java.sql.Date departureDate;
	private java.sql.Date arrivalDate;
	private boolean isDeleted;
	public int getTrainDetailId() {
		return trainDetailId;
	}
	public void setTrainDetailId(int trainDetailId) {
		this.trainDetailId = trainDetailId;
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
	public boolean isDeleted() {
		return isDeleted;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	@Override
	public ProcessableResource instantiate() {
		// TODO Auto-generated method stub
		return new TrainDTO();
	}
	
	
}
