package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Countries;

@RestController
public class Countriescontroller {
	
	private final CountriesService countryService;

    @Autowired
    public Countriescontroller (CountriesService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("countries")
    public List<Countries> getAllCountries() {
        return countryService.getAllCountries();
    }

}
