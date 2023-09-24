package com.eschool;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Event;
import com.eschool.beans.Travel;

public interface TravelRepository extends CrudRepository<Travel, Integer> {

	List<Travel> findAll();
	
	Travel findById(int id);
	
	long count();
	
	List<Travel> findAllByUserId(int id);
	
	List<Travel> findAllByEventId(int id);

	
	List<Travel> findAllByEventIdAndFamilyId(int eventId, int familyId);


}
