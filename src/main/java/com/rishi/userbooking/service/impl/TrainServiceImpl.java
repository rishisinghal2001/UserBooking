package com.rishi.userbooking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rishi.userbooking.DTO.TrainDTO;
import com.rishi.userbooking.common.OrikaObjectMapper;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.TrainEntity;
import com.rishi.userbooking.repository.TrainRepository;
import com.rishi.userbooking.service.ITrainService;

@Service("trainserviceimpl")
public class TrainServiceImpl implements ITrainService {

	@Autowired
	TrainRepository trainRepo;

	@Autowired
	OrikaObjectMapper orikaObjectMapper;

	@Override
	public TrainEntity saveTrain(TrainDTO train) {
		TrainEntity trainEn = new TrainEntity();
		trainEn = orikaObjectMapper.getMapper().map(train, TrainEntity.class);
		return trainRepo.save(trainEn);
	}

	@Override
	public void deleteTrain(int id) {
		Optional<TrainEntity> trainOp = trainRepo.findById(id);
		if (trainOp.isPresent()) {
			TrainEntity trainEn = trainOp.get();
			trainEn.setDeleted(true);
			trainRepo.save(trainEn);
			System.out.println("Deletion Completed");
		} else {
			System.out.println("ID doesnt exit");
		}
	}

	@Override
		public Page<TrainDTO> getAllTrain(int start,int pageSize){
			Pageable pageable =  PageRequest.of(start, pageSize);
			Page<TrainEntity> entityList = trainRepo.findAll(pageable);
	        List<TrainDTO> trainList = new ArrayList<>();
	        for (int i = 0; i < entityList.getContent().size(); i++) {
	     	   TrainDTO train = new TrainDTO();
	     	   if(entityList.getContent().get(i).isDeleted()==false) {
	     		  /* train.setArrivalDate(entityList.get(i).getArrivalDate());
	     		   train.setLastStation(entityList.get(i).getLastStation());
	     		   train.setTrainNo(entityList.get(i).getTrainNo());
	     		   train.setStartingStation(entityList.get(i).getStartingStation());
	     		   train.setDepartureDate(entityList.get(i).getDepartureDate()); */
	     		  train=orikaObjectMapper.getMapper().map(entityList.getContent().get(i), TrainDTO.class);
	     	   trainList.add(train);
	     	   }
	        }
	             
	        return new PageImpl<>(trainList, pageable, entityList.getTotalElements());
		}

	@Override
	public TrainDTO getTrainById(int id) {
		TrainDTO train = new TrainDTO();
		Optional<TrainEntity> trainOp = trainRepo.findById(id);
		if (trainOp.isPresent()) {
			TrainEntity trainEn = trainOp.get();
			/*
			 * train.setArrivalDate(trainEn.getArrivalDate());
			 * train.setLastStation(trainEn.getLastStation());
			 * train.setTrainNo(trainEn.getTrainNo());
			 * train.setStartingStation(trainEn.getStartingStation());
			 * train.setDepartureDate(trainEn.getDepartureDate());
			 */
			train = orikaObjectMapper.getMapper().map(trainEn, TrainDTO.class);

		}
		return train;
	}

	@Override
	public String trainValidation(int train_no) throws DuplicateEntryException {
		List<TrainDTO> trainList = new ArrayList<TrainDTO>();
		trainList = getAllTrain(0,199).getContent();
		for (int i = 0; i < trainList.size(); i++) {
			if (trainList.get(i).getTrainNo() == train_no)
				throw new DuplicateEntryException("Duplicate Entry");
		}
		return "Entry Saved Sucessfully";
	}
}
