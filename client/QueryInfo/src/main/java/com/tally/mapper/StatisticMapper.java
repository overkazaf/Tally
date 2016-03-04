package com.tally.mapper;

import java.util.ArrayList;

import com.tally.entity.StatisticEntity;

public interface StatisticMapper {
	public ArrayList<StatisticEntity> listStatisticEntity(String userId,String consumeType,String beginDate,String endDate);
}
