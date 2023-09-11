package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eschool.beans.Countries;

@Service
public class CountriesService {

	private final CountriesRepository countryRepository;

    @Autowired
    public CountriesService(CountriesRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Countries> getAllCountries() {
        return (List<Countries>) countryRepository.findAll();
    }
	
}
