package com.rishi.userbooking.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rishi.userbooking.entity.GuestReservationEntity;

@Repository
public interface GuestReservationRepository extends CrudRepository<GuestReservationEntity, Integer> {
   public List<GuestReservationEntity> findByGuestId(int guestId,Pageable pageble);
}
