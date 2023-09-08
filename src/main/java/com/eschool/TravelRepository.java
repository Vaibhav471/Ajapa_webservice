package com.eschool;

import org.springframework.data.repository.CrudRepository;

import com.eschool.beans.Travel;

public interface TravelRepository extends CrudRepository<Travel, Integer> {

	
}
