package com.rishi.userbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.rishi.userbooking.DTO.GuestReservationDTO;
import com.rishi.userbooking.common.OrikaObjectMapper;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.GuestReservationEntity;
import com.rishi.userbooking.repository.GuestReservationRepository;
import com.rishi.userbooking.service.IGuestReservationService;

@Service("guestreservationserviceimpl")
public class GuestReservationServiceImpl implements IGuestReservationService {

	@Autowired
	GuestReservationRepository guestReservationRepo;

	@Autowired
	OrikaObjectMapper orikaObjectMapper;

	@Override
	public GuestReservationEntity saveGuestReservation(GuestReservationDTO guestReservation) {
		GuestReservationEntity guestReservationEn = new GuestReservationEntity();
		guestReservationEn = orikaObjectMapper.getMapper().map(guestReservation, GuestReservationEntity.class);
		return guestReservationRepo.save(guestReservationEn);
	}

	@Override
	public void deleteGuestReservation(int id) {
		Optional<GuestReservationEntity> guestReservationOp = guestReservationRepo.findById(id);
		if (guestReservationOp.isPresent()) {
			GuestReservationEntity guestReservationEn = guestReservationOp.get();
			guestReservationEn.setDeleted(true);
			guestReservationRepo.save(guestReservationEn);
			System.out.println("Deletion Completed");
		} else {
			System.out.println("ID doesnt exit");
		}
	}

	@Override
	public List<GuestReservationDTO> getAllGuestReservation() {
		List<GuestReservationEntity> entityList = (List<GuestReservationEntity>) guestReservationRepo.findAll();
		List<GuestReservationDTO> guestReservationList = new ArrayList<GuestReservationDTO>();
		for (int i = 0; i < entityList.size(); i++) {
			GuestReservationDTO guestReservation = new GuestReservationDTO();
			if (entityList.get(i).isDeleted() == false) {
				/* 
				guestReservation.setBookedBy(entityList.get(i).getBookedBy());
				guestReservation.setGuestId(entityList.get(i).getGuestId());
				guestReservation.setGuestReservationId(entityList.get(i).getGuestReservationId());
				guestReservation.setCurrentStatus(entityList.get(i).isCurrentStatus());
				guestReservation.setReservationId(entityList.get(i).getReservationId());*/
				guestReservation=orikaObjectMapper.getMapper().map(entityList.get(i), GuestReservationDTO.class);
				guestReservationList.add(guestReservation);
			}
		}

		return guestReservationList;
	}

	@Override
	public GuestReservationDTO getGuestReservationById(int id) {
		GuestReservationDTO guestReservation = new GuestReservationDTO();
		Optional<GuestReservationEntity> guestReservationOp = guestReservationRepo.findById(id);
		if (guestReservationOp.isPresent()) {
			GuestReservationEntity guestReservationEn = guestReservationOp.get();
   /*		guestReservation.setBookedBy(guestReservationEn.getBookedBy());
			guestReservation.setGuestId(guestReservationEn.getGuestId());
			guestReservation.setGuestReservationId(guestReservationEn.getGuestReservationId());
			guestReservation.setCurrentStatus(guestReservationEn.isCurrentStatus());
			guestReservation.setReservationId(guestReservationEn.getReservationId()); */
			guestReservation=orikaObjectMapper.getMapper().map(guestReservationEn, GuestReservationDTO.class);
	               
		}
		return guestReservation;
	}

	@Override
	public String guestReservationValidation(int reservationId, int guestId) throws DuplicateEntryException {
		List<GuestReservationDTO> guestReservationList = new ArrayList<GuestReservationDTO>();
		guestReservationList = getAllGuestReservation();
		for (int i = 0; i < guestReservationList.size(); i++) {
			if (guestReservationList.get(i).getReservationId() == reservationId
					&& guestReservationList.get(i).getGuestId() == guestId)
				throw new DuplicateEntryException("Duplicate Entry");
		}
		return "Entry Saved Sucessfully";
	}

	public List<GuestReservationDTO> getGuestReservationByGuestId(int id,int start,int pageSize ) {
		List<GuestReservationDTO> guestReservation = new ArrayList<GuestReservationDTO>();
		List<GuestReservationEntity> guestReservationOp = guestReservationRepo.findByGuestId(id,PageRequest.of(start,pageSize,Sort.by(Direction.DESC,"guestReservationId")));
		if (!CollectionUtils.isEmpty(guestReservationOp)) {
			for (GuestReservationEntity g : guestReservationOp) {
				guestReservation.add(orikaObjectMapper.getMapper().map(g, GuestReservationDTO.class));
			}
		}
		return guestReservation;

	}

}
