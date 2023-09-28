package com.eschool.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="event_permission")
public class EventPermission {
	
	@Id
	int id;
	int eventId;
	String adminId;
	String canModify,canDelete;
	
	public EventPermission(int id, int eventId, String adminId, String canModify, String canDelete) {
		this.id = id;
		this.eventId = eventId;
		this.adminId = adminId;
		this.canModify = canModify;
		this.canDelete = canDelete;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
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

	public EventPermission() {
	}
	
	
}
