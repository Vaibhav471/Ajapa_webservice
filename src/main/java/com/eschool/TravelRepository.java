package com.eschool;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Travel;

public interface TravelRepository extends CrudRepository<Travel, Integer> {

	List<Travel> findAll();
	
	Travel findById(int id);
}
