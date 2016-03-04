package com.tally.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tally.entity.StatisticEntity;
import com.tally.service.StatisticService;
import com.tally.util.JSONUtil;

@Controller
public class StatisticController {
	@Autowired private StatisticService statisticService;
	
	@RequestMapping(value="/getstatistic",method=RequestMethod.GET)
	@ResponseBody public String getStatistic(String userId,String consumeType,String beginDate,String endDate)throws JSONException{
		JSONArray jsonArray = new JSONArray();
		try 
		{
			ArrayList<StatisticEntity> list = statisticService.listStatisticEntity(userId,consumeType, beginDate, endDate);
			for (StatisticEntity statisticEntity : list) {
				jsonArray.put(JSONUtil.entityToJsonStr(statisticEntity));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return jsonArray.toString();
	}
	
	
}
