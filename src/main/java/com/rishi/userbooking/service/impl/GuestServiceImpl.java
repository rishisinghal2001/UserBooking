package com.rishi.userbooking.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rishi.userbooking.DTO.GuestDTO;
import com.rishi.userbooking.DTO.GuestReservationDTO;
import com.rishi.userbooking.DTO.ReservationDTO;
import com.rishi.userbooking.common.OrikaObjectMapper;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.GuestEntity;
import com.rishi.userbooking.repository.GuestRepository;
import com.rishi.userbooking.service.IGuestReservationService;
import com.rishi.userbooking.service.IGuestService;
import com.rishi.userbooking.service.IReservationService;

@Service("guestserviceimpl")
public class GuestServiceImpl implements IGuestService {
	
	@Autowired
	GuestRepository guestRepo;
	
	@Autowired
	@Qualifier("guestreservationserviceimpl")
	IGuestReservationService guestreservationService;
	
	@Autowired
	@Qualifier("reservationserviceimpl")
	IReservationService reservationService;
	
	@Autowired 
	OrikaObjectMapper orikaObjectMapper;
	
	

	@Override
	public GuestEntity saveGuest(GuestDTO guest) {
		GuestEntity guestEn = new GuestEntity();
/*     //used when we dont use OrikaobjectMapper
 
		guestEn.setFirstName(guest.getFirstName());
		guestEn.setLastName(guest.getLastName());
		guestEn.setEmail(guest.getEmail());
		guestEn.setGender(guest.getGender());
		guestEn.setGuestId(guest.getGuestId());
*/	
	    guestEn = orikaObjectMapper.getMapper().map(guest, GuestEntity.class);
		
		return guestRepo.save(guestEn);
	}


	

	@Override
	public void deleteGuest(int id) {
		Optional <GuestEntity> guestOp = guestRepo.findById(id);
		if (guestOp.isPresent()) {
			Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
			GuestEntity guestEn = guestOp.get();
            guestEn.setDeleted(true);
            guestEn.setLastModifiedDate(lastModifiedDate);
            guestRepo.save(guestEn);
            System.out.println("Deletion Completed"); 
		}
		else {
			System.out.println("ID doesnt exit");
		}
	}

	@Override
	public Page<GuestDTO> getAllGuest(int start,int pageSize){
		Pageable pageable =  PageRequest.of(start, pageSize);
		Page<GuestEntity> pageGuestEntity = guestRepo.findAll(pageable);
        List<GuestDTO> guestList = new ArrayList<>();
        for (int i = 0; i < pageGuestEntity.getContent().size() ; i++) {
     	   GuestDTO guest = new GuestDTO();
     	   if(pageGuestEntity.getContent().get(i).isDeleted()==false) {
     		//it is used when we dont use orikamapper
     	   /* guest.setFirstName(entityList.get(i).getFirstName());
           guest.setLastName(entityList.get(i).getLastName());
           guest.setGender(entityList.get(i).getGender());
           guest.setEmail(entityList.get(i).getEmail()); */ 
     		   
     		   guest=orikaObjectMapper.getMapper().map(pageGuestEntity.getContent().get(i), GuestDTO.class );   
     		   guestList.add(guest); 				     
     	   }
        }
        System.out.println();
        return new PageImpl<>(guestList, pageable, pageGuestEntity.getTotalElements());
	}

	@Override
	public GuestDTO getGuestById(int id) {
			GuestDTO guest = new GuestDTO();
			Optional <GuestEntity> guestOp = guestRepo.findById(id);
			if (guestOp.isPresent()) {
				GuestEntity guestEn = guestOp.get();
				
				if(guestEn.isDeleted()== true) {
				   System.out.println("Id doesnt exit");	
	               }
				else {
					/*guest.setFirstName(guestEn.getFirstName());
		            guest.setLastName(guestEn.getLastName());
		            guest.setGender(guestEn.getGender());
		            guest.setEmail(guestEn.getEmail());*/ // used when orikamapping is not used
					guest=orikaObjectMapper.getMapper().map(guestEn,GuestDTO.class);
				    }
			    }
			else
			{
				System.out.println("Guest  not existed");
			}
	        return guest;
	}




	@Override
	public String guestValidation(String email) throws DuplicateEntryException {
		List <GuestDTO> guestList = new ArrayList<GuestDTO>();
		guestList = getAllGuest(0,199).getContent();
		for(int i=0;i<guestList.size();i++) {
			if(guestList.get(i).getEmail().equals(email))
				throw new DuplicateEntryException("Email Already Exists ");
		}
		return "Entry Saved Sucessfully";	
	}




	@Override
	public GuestDTO getAllReservationByGuestId(int id,int start,int pageSize) {
		GuestDTO guestDTO = new GuestDTO();
		guestDTO=getGuestById(id);
		if(guestDTO!=null) {
			 List <ReservationDTO> reservationList = new ArrayList<ReservationDTO>();
			 List <GuestReservationDTO> guestReservation = guestreservationService.getGuestReservationByGuestId(id,start,pageSize);
			 
			 for(int i=0;i<guestReservation.size();i++) {
					 reservationList.add(reservationService.getReservationById(guestReservation.get(i).getReservationId()));
			 }
			 guestDTO.setReservationList(reservationList);
		}
		 return  guestDTO;
	}




	@Override
	public GuestEntity updateGuest(int id,String fName, String lName ) {
		GuestDTO guestDTO = getGuestById(id);
		GuestEntity guestEntity = new GuestEntity();
		if(guestDTO==null)
			System.out.println("Wrong Guest ID");
		else {
			guestEntity = orikaObjectMapper.getMapper().map(guestDTO,GuestEntity.class);
			Timestamp lastModifiedDate = new Timestamp(System.currentTimeMillis());
		    guestEntity.setFirstName(fName);
		    guestEntity.setLastName(lName);
		    guestEntity.setLastModifiedDate(lastModifiedDate);
            guestRepo.save(guestEntity);
		    System.out.println("Sucessfully Changed");
		}
		
		return guestEntity;
	}
}
