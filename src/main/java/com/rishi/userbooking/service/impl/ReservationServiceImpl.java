package com.rishi.userbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.userbooking.DTO.ReservationDTO;
import com.rishi.userbooking.common.OrikaObjectMapper;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.ReservationEntity;
import com.rishi.userbooking.repository.ReservationRepository;
import com.rishi.userbooking.service.IReservationService;

@Service ("reservationserviceimpl")
public class ReservationServiceImpl implements IReservationService {
	
	@Autowired
	ReservationRepository reservationRepo;
	
	@Autowired 
	OrikaObjectMapper orikaObjectMapper;
	
	

	@Override
	public ReservationEntity saveReservation(ReservationDTO reservation){
		ReservationEntity reservationEn = new ReservationEntity();
	    reservationEn = orikaObjectMapper.getMapper().map(reservation, ReservationEntity.class);		
		return reservationRepo.save(reservationEn);
	}


	

	@Override
	public void deleteReservation(int id) {
		Optional <ReservationEntity> reservationOp = reservationRepo.findById(id);
		if (reservationOp.isPresent()) {
			ReservationEntity reservationEn = reservationOp.get();
            reservationEn.setDeleted(true);
            reservationRepo.save(reservationEn);
            System.out.println("Deletion Completed"); 
		}
		else {
			System.out.println("ID doesnt exit");
		}
	}

	@Override
	public List<ReservationDTO> getAllReservation(){
		List <ReservationEntity> entityList = (List<ReservationEntity>) reservationRepo.findAll();
        List <ReservationDTO> reservationList = new ArrayList<ReservationDTO>();
        for (int i = 0; i < entityList.size(); i++) {
     	   ReservationDTO reservation = new ReservationDTO();
     	   if(!entityList.get(i).isDeleted()) {
     	  /* reservation.setArrivalDate(entityList.get(i).getArrivalDate());
     	   reservation.setCurrentStatus(entityList.get(i).isCurrentStatus());
     	   reservation.setDepartureDate(entityList.get(i).getDepartureDate());
     	   reservation.setLastStation(entityList.get(i).getLastStation());
     	   reservation.setStartingStation(entityList.get(i).getStartingStation());
     	   reservation.setPnrNo(entityList.get(i).getPnrNo());
     	   reservation.setTrainNo(entityList.get(i).getTrainNo());  */
     	   reservation=orikaObjectMapper.getMapper().map(entityList.get(i), ReservationDTO.class);
     	   reservationList.add(reservation);
     	   }
        }   
             
        return reservationList;
	}

	@Override
	public ReservationDTO getReservationById(int id) {
			ReservationDTO reservation = new ReservationDTO();
			Optional <ReservationEntity> reservationOp = reservationRepo.findById(id);
			if (reservationOp.isPresent()) {
				   ReservationEntity reservationEn = reservationOp.get();
	  		    /*   reservation.setArrivalDate(reservationEn.getArrivalDate());
		     	   reservation.setCurrentStatus(reservationEn.isCurrentStatus());
		     	   reservation.setDepartureDate(reservationEn.getDepartureDate());
		     	   reservation.setLastStation(reservationEn.getLastStation());
		     	   reservation.setStartingStation(reservationEn.getStartingStation());
		     	   reservation.setPnrNo(reservationEn.getPnrNo());
		     	   reservation.setTrainNo(reservationEn.getTrainNo());*/
			       reservation=orikaObjectMapper.getMapper().map(reservationEn,ReservationDTO.class);
			}
			return reservation;
	}




	@Override
	public String reservationValidation(long pnr_no) throws DuplicateEntryException {
		List <ReservationDTO> reservationList = new ArrayList<ReservationDTO>();
		reservationList = getAllReservation();
		for(int i=0;i<reservationList.size();i++) {
			if(reservationList.get(i).getPnrNo()==pnr_no)
				throw new DuplicateEntryException("Duplicate Entry");
		}
		return "Entry Saved Sucessfully";	
	}
}

