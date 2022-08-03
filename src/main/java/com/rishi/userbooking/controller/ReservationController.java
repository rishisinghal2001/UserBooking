package com.rishi.userbooking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rishi.userbooking.DTO.ReservationDTO;
import com.rishi.userbooking.entity.ReservationEntity;
import com.rishi.userbooking.service.IReservationService;

@RestController
public class ReservationController {
	@Autowired
	@Qualifier("reservationserviceimpl")
	IReservationService reservationService;
	
	@GetMapping("/reservation")
	public ResponseEntity<ReservationDTO> getReservation(@RequestParam("id") int id) {
	   ReservationDTO reservation = reservationService.getReservationById(id);
	   return new ResponseEntity<ReservationDTO>(reservation, HttpStatus.OK);
	}
	
	@GetMapping("/reservations")
	public List<ReservationDTO> readReservation(){
		return reservationService.getAllReservation();
	}
	
	@PostMapping("/reservation")
	public ResponseEntity<ReservationEntity> saveReservation(@RequestBody ReservationDTO reservation) {
		ReservationEntity reservationEntity = new ReservationEntity();
		try {
			    System.out.println(reservationService.reservationValidation(reservation.getPnrNo()));
				reservationEntity = reservationService.saveReservation(reservation);
		}
		catch (DataIntegrityViolationException e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST," PNR no  Already Exists");
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<ReservationEntity>(reservationEntity,HttpStatus.OK) ;	
		
	    
	}
	
	@DeleteMapping("/reservation")
	public String deleteReservation(@RequestParam("id") int id) {
		   reservationService.deleteReservation(id);
		   return "Deleted Succesfully";
	}

}
