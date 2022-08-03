package com.rishi.userbooking.service;

import java.util.List;

import com.rishi.userbooking.DTO.ReservationDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.ReservationEntity;

public interface IReservationService {
  public ReservationEntity saveReservation(ReservationDTO reservation);
  public ReservationDTO getReservationById(int  id);
  public String reservationValidation(long l) throws DuplicateEntryException ;
  public void deleteReservation(int id);
  public  List<ReservationDTO>  getAllReservation() ;
  
}
