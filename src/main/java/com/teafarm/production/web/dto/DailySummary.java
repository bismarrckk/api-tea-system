package com.teafarm.production.web.dto;

import java.util.Date;

public interface DailySummary {
	
	String getName();
    String getRegNumber();
	double getField();
	double getReceipt();
	Date getForDate();

}
