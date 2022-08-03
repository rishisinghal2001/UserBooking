package com.rishi.userbooking.DTO;

public class GuestReservationDTO extends BaseDTO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int guestReservationId;
	private String bookedBy;
	private int reservationId;
	private int guestId;
	private boolean currentStatus;
	private boolean isDeleted;
	public int getGuestReservationId() {
		return guestReservationId;
	}
	public void setGuestReservationId(int guestReservationId) {
		this.guestReservationId = guestReservationId;
	}
	public String getBookedBy() {
		return bookedBy;
	}
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
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
	
	
}
