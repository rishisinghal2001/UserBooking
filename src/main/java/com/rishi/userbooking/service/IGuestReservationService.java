package com.rishi.userbooking.service;

import java.util.List;

import com.rishi.userbooking.DTO.GuestReservationDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.GuestReservationEntity;

public interface IGuestReservationService {
	/**
	 * This method saves the guest reservation
	 * @param guestReservation
	 * @return GuestReservationEntity 
	 */
  public GuestReservationEntity saveGuestReservation(GuestReservationDTO guestReservation);
  public GuestReservationDTO getGuestReservationById(int  id);
  public String guestReservationValidation(int reservationId,int guestId) throws DuplicateEntryException ;
  public void deleteGuestReservation(int id);
  public  List<GuestReservationDTO>  getAllGuestReservation() ;
  public List<GuestReservationDTO> getGuestReservationByGuestId(int id,int start,int pageSize);
  
}
