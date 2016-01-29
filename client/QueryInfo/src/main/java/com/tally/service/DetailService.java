package com.tally.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tally.entity.ConsumptionEntity;
import com.tally.mapper.DetailMapper;

@Service
public class DetailService {
	@Autowired DetailMapper detailMapper;
	public ArrayList<ConsumptionEntity> listConsumptionByDate(String userID,String beginDate,String endDate){
		
		return detailMapper.ListTodyDetailByUserID(userID, beginDate, endDate);
	}
	
	public Integer addConsume(ConsumptionEntity consume) {
		return detailMapper.addConsume(consume);
	}
}
