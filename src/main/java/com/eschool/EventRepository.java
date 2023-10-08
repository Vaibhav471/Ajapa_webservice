package com.eschool;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Event;
import com.eschool.beans.User;

public interface EventRepository extends CrudRepository<Event, Integer> {
	
	Event  findById(int id);	
	long count();	
	List<Event> findAllByStartDateGreaterThan(Date date);	
	List<Event> findAll();
	List<Event> findAllByOrderByStartDateDesc();
	@Query("SELECT u.eventName FROM Event u WHERE u.eventId = :id")
    String findEventNameByUserId(int id);
	
}
