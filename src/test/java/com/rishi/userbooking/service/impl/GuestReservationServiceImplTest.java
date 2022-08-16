package com.rishi.userbooking.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rishi.userbooking.DTO.GuestReservationDTO;


@SpringBootTest
public class GuestReservationServiceImplTest {
	@InjectMocks
    private GuestReservationServiceImpl guestReservationServiceImpl;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getGuestReservationByIdTest() {
		GuestReservationDTO guest =guestReservationServiceImpl.getGuestReservationById(3);
		assertNotNull(guest);
	}
}
