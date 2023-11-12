package com.eschool;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Attendance;
import com.eschool.beans.Event;
import com.eschool.beans.User;

public interface AttendanceRepository  extends CrudRepository<Attendance, Integer> {
	
	Attendance findByUserIdAndEventId(int userId,int eventId);
}
