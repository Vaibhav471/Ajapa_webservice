package com.eschool.beans;

import java.sql.Blob;
import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {
	
	@Id
	private int event_id;
	private String eventName,event_type,event_location,listed_by,start_time,end_time;
	private int event_status;
	private Blob other;
	Date startDate;
	Date endDate;
	
	
	
	
	
	
	public Event() {
		
	}






	public Event(int event_id, String eventName, String event_type, String event_location, String listed_by,
			String start_time, String end_time, int event_status, Date startDate, Date endDate) {
		this.event_id = event_id;
		this.eventName = eventName;
		this.event_type = event_type;
		this.event_location = event_location;
		this.listed_by = listed_by;
		this.start_time = start_time;
		this.end_time = end_time;
		this.event_status = event_status;
		this.startDate = startDate;
		this.endDate = endDate;
	}






	public Event(int event_id, String eventName, String event_type, String event_location, String listed_by,
			String start_time, String end_time, int event_status, Blob other, Date startDate, Date endDate) {
		this.event_id = event_id;
		this.eventName = eventName;
		this.event_type = event_type;
		this.event_location = event_location;
		this.listed_by = listed_by;
		this.start_time = start_time;
		this.end_time = end_time;
		this.event_status = event_status;
		this.other = other;
		this.startDate = startDate;
		this.endDate = endDate;
	}






	public int getEvent_id() {
		return event_id;
	}






	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}






	






	public String getEventName() {
		return eventName;
	}






	public void setEventName(String eventName) {
		this.eventName = eventName;
	}






	public String getEvent_type() {
		return event_type;
	}






	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}






	public String getEvent_location() {
		return event_location;
	}






	public void setEvent_location(String event_location) {
		this.event_location = event_location;
	}






	public String getListed_by() {
		return listed_by;
	}






	public void setListed_by(String listed_by) {
		this.listed_by = listed_by;
	}






	public String getStart_time() {
		return start_time;
	}






	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}






	public String getEnd_time() {
		return end_time;
	}






	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}






	public int getEvent_status() {
		return event_status;
	}






	public void setEvent_status(int event_status) {
		this.event_status = event_status;
	}






	public Blob getOther() {
		return other;
	}






	public void setOther(Blob other) {
		this.other = other;
	}






	public Date getStartDate() {
		return startDate;
	}






	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}




	public Date getEndDate() {
		return endDate;
	}



	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	
}
