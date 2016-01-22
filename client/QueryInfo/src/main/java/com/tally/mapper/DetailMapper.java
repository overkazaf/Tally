package com.tally.mapper;

import java.util.List;

import com.tally.entity.ConsumptionEntity;

public interface DetailMapper {
	List<ConsumptionEntity> ListTodyDetailByUserID();
}
