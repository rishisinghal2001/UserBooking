package com.rishi.userbooking.service;

import java.util.List;

import com.rishi.userbooking.DTO.UserDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.UserEntity;

public interface IUserService {
	public UserEntity saveUser(UserDTO user);
	  public UserDTO getUserById(int  id);
	  public String userValidation(String email) throws DuplicateEntryException ;
	  public void deleteUser(int id);
	  public  List<UserDTO>  getAllUser() ;
}
