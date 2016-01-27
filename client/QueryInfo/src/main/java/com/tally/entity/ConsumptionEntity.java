package com.tally.entity;

import java.math.BigDecimal;



public class ConsumptionEntity {
	public String UserID;
	public String ConsumName;
	public String ConsumType;
	public String Location;
	public BigDecimal Cost;
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
	public BigDecimal getCost() {
		return Cost;
	}
	public void setCost(BigDecimal cost) {
		Cost = cost;
	}
}
