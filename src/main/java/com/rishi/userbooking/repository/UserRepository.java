package com.rishi.userbooking.repository;

import org.springframework.data.repository.CrudRepository;

import com.rishi.userbooking.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
