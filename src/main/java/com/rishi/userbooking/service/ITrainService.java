package com.rishi.userbooking.service;

import org.springframework.data.domain.Page;

import com.rishi.userbooking.DTO.TrainDTO;
import com.rishi.userbooking.customexception.DuplicateEntryException;
import com.rishi.userbooking.entity.TrainEntity;

public interface ITrainService {
  public TrainEntity saveTrain(TrainDTO train);
  public TrainDTO getTrainById(int  id);
  public String trainValidation(int train_no) throws DuplicateEntryException;
  public void deleteTrain(int id);
  public  Page<TrainDTO>  getAllTrain(int start,int pageSize) ;
  
}
