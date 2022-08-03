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

import com.rishi.userbooking.DTO.UserDTO;
import com.rishi.userbooking.entity.UserEntity;
import com.rishi.userbooking.service.IUserService;

@RestController
public class UserController {
	@Autowired 
	@Qualifier("userserviceimpl")
	IUserService userService;
	
	@GetMapping("/user")
	public ResponseEntity<UserDTO> getUser(@RequestParam("id") int id) {
	   UserDTO user = userService.getUserById(id);
	   return new ResponseEntity<UserDTO>(user, HttpStatus.OK);
	}
	
	@GetMapping("/users")
	public List<UserDTO> readUsers(){
		return userService.getAllUser();
	}
	
	@PostMapping("/user")
	public ResponseEntity<UserEntity> saveUser(@RequestBody UserDTO user) {
		UserEntity userEntity = new UserEntity();
		try {
			    System.out.println(userService.userValidation(user.getEmail()));
				userEntity = userService.saveUser(user);
		}
		catch (DataIntegrityViolationException e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.BAD_REQUEST," Email Already Exists");
		}
		catch(Exception e) {
			System.out.println(e);
	    	throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"INTERNAL_SERVER_ERROR");
	    }
	    return new ResponseEntity<UserEntity>(userEntity,HttpStatus.OK) ;	
		
	    
	}
	
	@DeleteMapping("/user")
	public String deleteUser(@RequestParam("id") int id) {
		   userService.deleteUser(id);
		   return "Deleted Succesfully";
	}
}
