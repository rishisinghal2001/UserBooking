package com.rishi.userbooking.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishi.userbooking.DTO.UserDTO;
import com.rishi.userbooking.common.OrikaObjectMapper;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.UserEntity;
import com.rishi.userbooking.repository.UserRepository;
import com.rishi.userbooking.service.IUserService;

@Service ("userserviceimpl")
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired 
	OrikaObjectMapper orikaObjectMapper;
	

	@Override
	public UserEntity saveUser(UserDTO user) {
		UserEntity userEn = new UserEntity();
        userEn = orikaObjectMapper.getMapper().map(user, UserEntity.class);
		
		return userRepo.save(userEn);
	}

	@Override
	public UserDTO getUserById(int id) {
		UserDTO user = new UserDTO();
		Optional <UserEntity> userOp = userRepo.findById(id);
		if (userOp.isPresent()) {
			UserEntity userEn = userOp.get();
           /* user.setFirstName (userEn.getFirstName());
            user.setLastName(userEn.getLastName());
            user.setGender(userEn.getGender());
            user.setEmail(userEn.getEmail());
            user.setUserpassword(userEn.getUserpassword()); */
			user=orikaObjectMapper.getMapper().map(userEn, UserDTO.class);

            }
        return user;
	}

	@Override
	public String userValidation(String email) throws DuplicateEntryException {
		List <UserDTO> userList = new ArrayList<UserDTO>();
		userList = getAllUser();
		for(int i=0;i<userList.size();i++) {
			if(userList.get(i).getEmail().equals(email))
				throw new DuplicateEntryException("Duplicate Entry");
		}
		return "Entry Saved Sucessfully";	
	}

	@Override
	public void deleteUser(int id) {
		Optional <UserEntity> userOp = userRepo.findById(id);
		if (userOp.isPresent()) {
			UserEntity userEn = userOp.get();
            userEn.setDeleted(true);
            userEn.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
            userRepo.save(userEn);
            System.out.println("Deletion Completed"); 
		}
		else {
			System.out.println("ID doesnt exit");
		}
		
	}

	@Override
	public List<UserDTO> getAllUser() {
		List <UserEntity> entityList = (List<UserEntity>) userRepo.findAll();
        List <UserDTO> userList = new ArrayList<UserDTO>();
        for (int i = 0; i < entityList.size(); i++) {
     	   UserDTO user = new UserDTO();
     	   if(entityList.get(i).isDeleted()==false) {
     	  /* user.setFirstName(entityList.get(i).getFirstName());
           user.setLastName(entityList.get(i).getLastName());
           user.setGender(entityList.get(i).getGender());
           user.setEmail(entityList.get(i).getEmail());*/
     		  user=orikaObjectMapper.getMapper().map(entityList.get(i), UserDTO.class);
     	   userList.add(user);
     	   }
        }     
        System.out.println(entityList.size());
        return userList;

	}

}
