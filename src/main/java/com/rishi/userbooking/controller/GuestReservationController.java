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

import com.rishi.userbooking.DTO.GuestReservationDTO;
import com.rishi.userbooking.entity.GuestReservationEntity;
import com.rishi.userbooking.service.IGuestReservationService;

@RestController
public class GuestReservationController {
	@Autowired
	@Qualifier("guestreservationserviceimpl")
	IGuestReservationService guestreservationService;
	
	@GetMapping("/guestreservation")
	public ResponseEntity<GuestReservationDTO> getGuestReservations(@RequestParam("id") int id) {
	   GuestReservationDTO guestReservation = guestreservationService.getGuestReservationById(id);
	   return new ResponseEntity<GuestReservationDTO>(guestReservation, HttpStatus.OK);
	}
	
	@GetMapping("/guestreservations")
	public List<GuestReservationDTO> readGuestReservation(){
		return guestreservationService.getAllGuestReservation();
	}
	
	@PostMapping("/guestreservation")
	public ResponseEntity<GuestReservationEntity> saveGuestReservation(@RequestBody GuestReservationDTO guestReservation) {
		GuestReservationEntity guestReservationEntity = new GuestReservationEntity();
		try {
			    System.out.println(guestreservationService.guestReservationValidation(guestReservation.getReservationId(),guestReservation.getGuestId()));
				guestReservationEntity = guestreservationService.saveGuestReservation(guestReservation);
		}
		catch (DataIntegrityViolationException e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Same guestid with reservation id  Already Exists");
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<GuestReservationEntity>(guestReservationEntity,HttpStatus.OK) ;	
		
	    
	}
	
	@DeleteMapping("/guestReservation")
	public String deleteGuestReservation(@RequestParam("id") int id) {
		   guestreservationService.deleteGuestReservation(id);
		   return "Deleted Succesfully";
	}
		
}
