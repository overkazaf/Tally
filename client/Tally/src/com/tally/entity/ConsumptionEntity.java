package com.tally.entity;

import java.math.BigDecimal;

import android.R.string;



public class ConsumptionEntity {
	public String Id;
	public String UserID;
	public String ConsumName;
	public String ConsumType;
	public String Location;
	public String Cost;
	
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getUserID() {
		return UserID;
	}
	public void setUserID(String userID) {
		UserID = userID;
	}
	public String getConsumName() {
		return ConsumName;
	}
	public void setConsumName(String consumName) {
		ConsumName = consumName;
	}
	public String getConsumType() {
		return ConsumType;
	}
	public void setConsumType(String consumType) {
		ConsumType = consumType;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getCost() {
		return Cost;
	}
	public void setCost(String cost) {
		Cost = cost;
	}
	
	public ConsumptionEntity(){
		
	}
	
	public ConsumptionEntity(String id, String name,String cost,String type,String location){
		Id = id;
		ConsumName = name;
		ConsumType = type;
		Cost = cost;
		Location = location;
	}
}
