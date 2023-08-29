package com.eschool;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Event;
import com.eschool.beans.User;

public interface EventRepository extends CrudRepository<Event, Integer> {
	
	Event  findById(int id);

}
