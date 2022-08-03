package com.rishi.userbooking.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

@Entity(name="guestreservationdetail")
@Table(name="guestreservationdetail")
@Where(clause = "is_deleted = 'false'")
public class GuestReservationEntity extends BaseEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer guestReservationId;
	private String bookedBy;
	private Integer reservationId;
	private Integer guestId;
	private boolean currentStatus;
	private boolean isDeleted;
	public Integer getGuestReservationId() {
		return guestReservationId;
	}
	public void setGuestReservationId(Integer guestReservationId) {
		this.guestReservationId = guestReservationId;
	}
	public String getBookedBy() {
		return bookedBy;
	}
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}
	public Integer getReservationId() {
		return reservationId;
	}
	public void setReservationId(Integer reservationId) {
		this.reservationId = reservationId;
	}
	public Integer getGuestId() {
		return guestId;
	}
	public void setGuestId(Integer guestId) {
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
