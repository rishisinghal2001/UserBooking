package com.rishi.userbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rishi.userbooking.entity.GuestEntity;

@Repository
public interface GuestRepository extends JpaRepository<GuestEntity,Integer>  {
	
	

	
    
}
