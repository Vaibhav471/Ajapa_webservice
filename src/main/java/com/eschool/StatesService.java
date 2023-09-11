package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.States;

@Service
public class StatesService {

	private final StatesRepository statesRepository;

    @Autowired
    public StatesService(StatesRepository statesRepository) {
        this.statesRepository = statesRepository;
    }

    public List<States> getStatesByCountryId(Long countryId) {
        return statesRepository.findByCountryId(countryId);
    }
	
}
