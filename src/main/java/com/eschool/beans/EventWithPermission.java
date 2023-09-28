package com.eschool.beans;

import java.sql.Blob;
import java.util.Date;

public class EventWithPermission {
	
	private int eventId;
	private String eventName,eventType,eventLocation,listedBy,startTime,endTime;
	private int eventStatus;
	private Blob other;
	Date startDate;
	Date endDate;
	 private Date lockArrivalDate;
	    
	    private Date lockDepartureDate;
	    
	    private String canModify;
	    private String canDelete;
	    
		public EventWithPermission(int eventId, String eventName, String eventType, String eventLocation,
				String listedBy, String startTime, String endTime, int eventStatus, Blob other, Date startDate,
				Date endDate, Date lockArrivalDate, Date lockDepartureDate, String canModify, String canDelete) {
			this.eventId = eventId;
			this.eventName = eventName;
			this.eventType = eventType;
			this.eventLocation = eventLocation;
			this.listedBy = listedBy;
			this.startTime = startTime;
			this.endTime = endTime;
			this.eventStatus = eventStatus;
			this.other = other;
			this.startDate = startDate;
			this.endDate = endDate;
			this.lockArrivalDate = lockArrivalDate;
			this.lockDepartureDate = lockDepartureDate;
			this.canModify = canModify;
			this.canDelete = canDelete;
		}

		public EventWithPermission() {
		}

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

		public Date getLockArrivalDate() {
			return lockArrivalDate;
		}

		public void setLockArrivalDate(Date lockArrivalDate) {
			this.lockArrivalDate = lockArrivalDate;
		}

		public Date getLockDepartureDate() {
			return lockDepartureDate;
		}

		public void setLockDepartureDate(Date lockDepartureDate) {
			this.lockDepartureDate = lockDepartureDate;
		}

		public String getCanModify() {
			return canModify;
		}

		public void setCanModify(String canModify) {
			this.canModify = canModify;
		}

		public String getCanDelete() {
			return canDelete;
		}

		public void setCanDelete(String canDelete) {
			this.canDelete = canDelete;
		}
	    
	    

}
