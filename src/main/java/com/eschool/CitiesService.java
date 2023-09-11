package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.Cities;

@Service
public class CitiesService {
	
	private final CitiesRepository citiesRepository;

    @Autowired
    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public List<Cities> getCitiesByStateId(int stateId) {
        return citiesRepository.findByStateId(stateId); // Use findByState_id
    }

}
