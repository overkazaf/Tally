package com.tally.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class StatisticEntity {
	private BigDecimal Cost;
	private Timestamp MDate;
	public Timestamp getMDate() {
		return MDate;
	}
	public void setMDate(Timestamp mDate) {
		MDate = mDate;
	}
	public BigDecimal getCost() {
		return Cost;
	}
	public void setCost(BigDecimal cost) {
		Cost = cost;
	}
}
