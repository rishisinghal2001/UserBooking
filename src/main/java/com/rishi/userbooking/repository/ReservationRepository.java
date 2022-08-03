package com.rishi.userbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rishi.userbooking.entity.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Integer> {

}
