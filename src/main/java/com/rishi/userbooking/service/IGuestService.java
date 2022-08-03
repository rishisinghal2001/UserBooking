package com.rishi.userbooking.service;

import org.springframework.data.domain.Page;

import com.rishi.userbooking.DTO.GuestDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.GuestEntity;

public interface IGuestService {
  public GuestEntity saveGuest(GuestDTO guest);
  public GuestEntity updateGuest(int id , String fName, String lName);
  public GuestDTO getGuestById(int  id);
  public String guestValidation(String email) throws DuplicateEntryException ;
  public void deleteGuest(int id);
  public  Page<GuestDTO>  getAllGuest(int start,int pageSize) ;
  public  GuestDTO  getAllReservationByGuestId(int id,int start,int pageSize) ;
  
}
