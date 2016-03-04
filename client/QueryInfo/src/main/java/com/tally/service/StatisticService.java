package com.tally.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tally.entity.StatisticEntity;
import com.tally.mapper.StatisticMapper;

@Service
public class StatisticService {
	@Autowired StatisticMapper statisticMapper;
	public ArrayList<StatisticEntity> listStatisticEntity(String userId,String consumeType,String beginDate,String endDate){
		return statisticMapper.listStatisticEntity(userId,consumeType, beginDate, endDate);
	}
}
