package com.rishi.userbooking.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rishi.userbooking.DTO.TrainDTO;
import com.rishi.userbooking.entity.TrainEntity;
import com.rishi.userbooking.service.ITrainService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
public class TrainController {
	@Autowired
	@Qualifier("trainserviceimpl")
	ITrainService trainService;
	
	@Autowired
	private PagedResourcesAssembler<TrainDTO> trainAssembler;
	
	
	@Operation(summary = "Get a train Record")
	@Schema(implementation = TrainEntity.class)
	@GetMapping("/train")
	public ResponseEntity<TrainDTO> getTrain(@RequestParam("id") int id) {
	   TrainDTO train = trainService.getTrainById(id);
	   return new ResponseEntity<TrainDTO>(train, HttpStatus.OK);
	}
	
	

	@Operation(summary = "Get All Train Records")
	@Schema(implementation = TrainEntity.class)
	@GetMapping("/trains")
	public ResponseEntity<PagedModel<EntityModel<TrainDTO>>> findAllTrain(@RequestParam(required = false , defaultValue = "0") int start,@RequestParam("pageSize") int pageSize){
		 Page<TrainDTO> pageTrainDto = trainService.getAllTrain(start,pageSize);
		return new ResponseEntity<>(trainAssembler.toModel(pageTrainDto), HttpStatus.OK);
		
	}
	
	
	@Operation(summary = "Save a train Record")
	@Schema(implementation = TrainEntity.class)
	@PostMapping("/train")
	public ResponseEntity<TrainEntity> saveTrain(@RequestBody TrainDTO train) {
		TrainEntity trainEntity = new TrainEntity();
		try {
			    System.out.println(trainService.trainValidation(train.getTrainNo()));
				trainEntity = trainService.saveTrain(train);
		}
		catch (DataIntegrityViolationException e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Train Id  Already Exists");
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<TrainEntity>(trainEntity,HttpStatus.OK) ;	
		
	    
	}
	
	
	@Operation(summary = "Delete a train Record")
	@Schema(implementation = TrainEntity.class)
	@DeleteMapping("/train")
	public String deleteTrain(@RequestParam("id") int id) {
		   trainService.deleteTrain(id);
		   return "Deleted Succesfully";
	}

}
