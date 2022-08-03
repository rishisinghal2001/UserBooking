package com.rishi.userbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rishi.userbooking.entity.TrainEntity;

public interface TrainRepository extends JpaRepository<TrainEntity,Integer> {
   
}
