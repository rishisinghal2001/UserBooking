package com.rishi.userbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rishi.userbooking.DTO.GuestDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.GuestEntity;
import com.rishi.userbooking.service.IGuestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class GuestController {
	@Autowired
	@Qualifier ("guestserviceimpl")
	IGuestService guestService;
	
	
	@Operation(summary = "Get all reservation of a  guest by its id")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Found the Guest", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest not found", 
	    content = @Content) })
	@GetMapping("/getallreservationbyguestid")
	public GuestDTO readguests(@RequestParam("id") int id,@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
		return guestService.getAllReservationByGuestId(id,start,pageSize);
	}
    
	@Operation(summary = "Get a guest by its id")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Found the Guest", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest not found", 
	    content = @Content) })
	@GetMapping("/guest")
	public ResponseEntity<GuestDTO> getGuest(@RequestParam("id") int id) {
	   GuestDTO guest = guestService.getGuestById(id);
	   return new ResponseEntity<GuestDTO>(guest, HttpStatus.OK);
	}
	
	
	@Operation(summary = "Get a guest by its id")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Found the Guest", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest not found", 
	    content = @Content) })
	@GetMapping("/guests")
	public Page<GuestDTO> findallguest(@RequestParam("start")int start,@RequestParam("pageSize") int pageSize){
		return guestService.getAllGuest(start,pageSize);
	}

	@Operation(summary = "Save a guest by GuestDTO")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Save the Guest", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid details supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest not saved", 
	    content = @Content) })
	@PostMapping("/guest")
	public ResponseEntity<GuestEntity> saveGuest(@RequestBody GuestDTO guest) throws DuplicateEntryException {
		GuestEntity guestEntity = new GuestEntity();
		try {
			    System.out.println(guestService.guestValidation(guest.getEmail()));
				guestEntity = guestService.saveGuest(guest);
		}
		catch (DuplicateEntryException e) {
			System.out.println(e);
	    	throw e;
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<GuestEntity>(guestEntity,HttpStatus.OK) ;	
		
	    
	}
	
	@Operation(summary = "Update a guest by Its id")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Update the Guest", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid  Guest id supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest is not updated", 
	    content = @Content) })
	@PutMapping("/guest")
	public ResponseEntity<GuestEntity> updateGuest(@RequestParam("id") int id,@RequestParam("fName") String fName,@RequestParam("lName") String lName){
		GuestEntity guestEntity = new GuestEntity();
		try {
			    guestEntity = guestService.updateGuest(id,fName,lName);
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<GuestEntity>(guestEntity,HttpStatus.OK) ;	   
	}
	
	@Operation(summary = "Delete a guest by its id")
	@ApiResponses(value = { 
	@ApiResponse(responseCode = "200", description = "Delete the Guest Sucessfully", 
	    content = { @Content(mediaType = "application/json", 
	    schema = @Schema(implementation = GuestEntity.class)) }),
	@ApiResponse(responseCode = "400", description = "Invalid id supplied", 
	    content = @Content), 
	@ApiResponse(responseCode = "404", description = "Guest not found", 
	    content = @Content) })
	@DeleteMapping("/guest")
	public String deleteGuest(@RequestParam("id") int id) {
		   guestService.deleteGuest(id);
		   return "Deleted Succesfully";
	}
		
}
