package com.tally.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tally.entity.ConsumptionEntity;
import com.tally.service.DetailService;
import com.tally.util.JSONUtil;

@Controller
public class DetailController {
	@Autowired private DetailService detailService;
	
	@RequestMapping(value="/todaydetail",method=RequestMethod.GET)
	@ResponseBody public String listTodayDetailsByDate(String userID,String beginDate,String endDate) throws JSONException{
		Date bgDate = new Date();
		Date edDate = new Date();
		JSONArray jsonArray = new JSONArray();
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(beginDate != null && endDate != null){
				bgDate = sdf.parse(beginDate);
				edDate = sdf.parse(endDate);
			}	
			ArrayList<ConsumptionEntity> result = detailService.listConsumptionByDate(userID,sdf.format(bgDate)+" 00:00:00",sdf.format(edDate)+" 23:59:59");
			
			if(result != null && result.size() != 0){
				for (ConsumptionEntity consumptionEntity : result) {
					jsonArray.put(JSONUtil.entityToJsonStr(consumptionEntity));
				}
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
    	return jsonArray.toString();
	}
	
	
	@RequestMapping(value="/addconsume",method=RequestMethod.GET)
	@ResponseBody public String addconsume(String userID,String consumeType,String consumeName,String cost,String location) throws JSONException{
		JSONObject jsonObject = new JSONObject();
		ConsumptionEntity consume = new ConsumptionEntity();
		try{
			consume.setUserID(userID);
			consume.setConsumName(consumeName);
			consume.setConsumType(consumeType);
			consume.setLocation(location);
			consume.setCost(BigDecimal.valueOf(Double.parseDouble(cost)));
			detailService.addConsume(consume);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return jsonObject.toString();
	}
}
