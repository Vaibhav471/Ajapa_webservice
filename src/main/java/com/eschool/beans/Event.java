package com.eschool.beans;

import java.sql.Blob;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event")
public class Event {
	
	@Id
	private int event_id;
	private String event_name,event_type,event_location,start_date,end_date,listed_by;
	private int event_status;
	private Blob other;
	
	
	
	
	public Event(int event_id, String event_name, String event_type, String event_location, String start_date,
			String end_date, String listed_by, int event_status, Blob other) {
		super();
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_type = event_type;
		this.event_location = event_location;
		this.start_date = start_date;
		this.end_date = end_date;
		this.listed_by = listed_by;
		this.event_status = event_status;
		this.other = other;
	}


	public Event( String event_name, String event_type, String event_location, String start_date,
			String end_date, String listed_by, int event_status) {
		this.event_name = event_name;
		this.event_type = event_type;
		this.event_location = event_location;
		this.start_date = start_date;
		this.end_date = end_date;
		this.listed_by = listed_by;
		this.event_status = event_status;
	}


	public Event() {

	
	}


	public int getEvent_id() {
		return event_id;
	}


	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}


	public String getEvent_name() {
		return event_name;
	}


	public void setEvent_name(String event_name) {
		this.event_name = event_name;
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


	public String getStart_date() {
		return start_date;
	}


	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}


	public String getEnd_date() {
		return end_date;
	}


	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}


	public String getListed_by() {
		return listed_by;
	}


	public void setListed_by(String listed_by) {
		this.listed_by = listed_by;
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
	
	
	
	
	
	

}
