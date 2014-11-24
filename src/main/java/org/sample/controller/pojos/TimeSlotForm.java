package org.sample.controller.pojos;

import java.util.Date;

public class TimeSlotForm {
	private Date date;
	private Date time;
	private int maxNumVisitors;
	private long adId;
	private String category;
	
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getMaxNumVisitors() {
		return maxNumVisitors;
	}
	public void setMaxNumVisitors(int maxNumVisitors) {
		this.maxNumVisitors = maxNumVisitors;
	}
	public long getAdId() {
		return adId;
	}
	public void setAdId(long adId) {
		this.adId = adId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	

}
