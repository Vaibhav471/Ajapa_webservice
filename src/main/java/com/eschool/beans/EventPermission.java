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
	int adminId;
	String canRead,canWrite;
	
	public EventPermission(int id, int eventId, int adminId, String canRead, String canWrite) {
		this.id = id;
		this.eventId = eventId;
		this.adminId = adminId;
		this.canRead = canRead;
		this.canWrite = canWrite;
	}

	public EventPermission() {

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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public String getCanRead() {
		return canRead;
	}

	public void setCanRead(String canRead) {
		this.canRead = canRead;
	}

	public String getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(String canWrite) {
		this.canWrite = canWrite;
	}
	
	

}
