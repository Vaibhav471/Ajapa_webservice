package com.eschool;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Event;

public interface EventRepo extends CrudRepository<Event, Integer> {
}
