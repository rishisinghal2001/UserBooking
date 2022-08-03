package com.rishi.userbooking.common;

import org.springframework.stereotype.Component;

import com.rishi.userbooking.DTO.GuestDTO;
import com.rishi.userbooking.DTO.GuestReservationDTO;
import com.rishi.userbooking.DTO.ReservationDTO;
import com.rishi.userbooking.entity.GuestEntity;
import com.rishi.userbooking.entity.GuestReservationEntity;
import com.rishi.userbooking.entity.ReservationEntity;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

@Component
public class OrikaObjectMapper {
	private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
    private MapperFacade mapper=mapperFactory.getMapperFacade();
	
	static  {
		mapperFactory.classMap(GuestDTO.class, GuestEntity.class);  
		mapperFactory.classMap(GuestEntity.class, GuestDTO.class);
		mapperFactory.classMap(GuestReservationEntity.class, GuestReservationDTO.class);
		mapperFactory.classMap(GuestReservationDTO.class, GuestReservationEntity.class);
		mapperFactory.classMap(ReservationEntity.class, ReservationDTO.class);
		mapperFactory.classMap(ReservationDTO.class, ReservationEntity.class);	
	}
	
	
	public MapperFacade getMapper() {
		return mapper;
	}
}
