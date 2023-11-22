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
	private int eventId;
	private String eventName,eventType,eventLocation,listedBy,startTime,endTime;
	private int eventStatus=1,bookingStatus=1;
	private Date startDate,endDate,lockArrivalDate,lockDepartureDate,shivirStartDate,shivirEndDate;
	private boolean shivirAvailable;	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getListedBy() {
		return listedBy;
	}
	public void setListedBy(String listedBy) {
		this.listedBy = listedBy;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public int getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(int eventStatus) {
		this.eventStatus = eventStatus;
	}
	public int getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(int bookingStatus) {
		this.bookingStatus = bookingStatus;
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
	public Date getLockArrivalDate() {
		return lockArrivalDate;
	}
	public void setLockArrivalDate(Date lockArrivalDate) {
		this.lockArrivalDate = lockArrivalDate;
	}
	public Date getLockDepartureDate() {
		return lockDepartureDate;
	}
	public Event(int eventId, String eventName, String eventType, String eventLocation, String listedBy,
			String startTime, String endTime, int eventStatus, int bookingStatus, Date startDate, Date endDate,
			Date lockArrivalDate, Date lockDepartureDate, Date shivirStartDate, Date shivirEndDate,
			boolean shivirAvailable) {
	
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.listedBy = listedBy;
		this.startTime = startTime;
		this.endTime = endTime;
		this.eventStatus = eventStatus;
		this.bookingStatus = bookingStatus;
		this.startDate = startDate;
		this.endDate = endDate;
		this.lockArrivalDate = lockArrivalDate;
		this.lockDepartureDate = lockDepartureDate;
		this.shivirStartDate = shivirStartDate;
		this.shivirEndDate = shivirEndDate;
		this.shivirAvailable = shivirAvailable;
	}
	public void setLockDepartureDate(Date lockDepartureDate) {
		this.lockDepartureDate = lockDepartureDate;
	}
	
	
	public boolean getShivirAvailable() {
		return shivirAvailable;
	}
	public void setShivirAvailable(boolean shivirAvailable) {
		this.shivirAvailable = shivirAvailable;
	}
	public Date getShivirStartDate() {
		return shivirStartDate;
	}
	public void setShivirStartDate(Date shivirStartDate) {
		this.shivirStartDate = shivirStartDate;
	}
	public Date getShivirEndDate() {
		return shivirEndDate;
	}
	public void setShivirEndDate(Date shivirEndDate) {
		this.shivirEndDate = shivirEndDate;
	}
	public Event() {
		
	}
}