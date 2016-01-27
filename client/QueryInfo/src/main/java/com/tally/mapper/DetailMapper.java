package com.tally.mapper;

import java.util.ArrayList;

import com.tally.entity.ConsumptionEntity;

public interface DetailMapper {
	public ArrayList<ConsumptionEntity> ListTodyDetailByUserID(String userID,String beginDate,String endDate);
}
