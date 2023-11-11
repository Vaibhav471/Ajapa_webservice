package com.eschool.beans;
import jakarta.persistence.Id;
public class Attendance {
	@Id
    private int attendanceId;
    private int eventId;
    private int userId;
    public Attendance(int attendanceId, int eventId, int userId) {
		this.attendanceId = attendanceId;
		this.eventId = eventId;
		this.userId = userId;
	}
	public Attendance() {		
	}
	public int getAttendanceId() {
		return attendanceId;
	}
	public void setAttendanceId(int attendanceId) {
		this.attendanceId = attendanceId;
	}
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
