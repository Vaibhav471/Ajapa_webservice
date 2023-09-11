package com.eschool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Cities;
import com.eschool.beans.States;

@RestController
public class CitiesController {
	
	private final CitiesService citiesService;


    @Autowired
    public CitiesController(CitiesService citiesService) {
        this.citiesService = citiesService;
    }

    @GetMapping("/bystate/{stateId}")
    public List<Cities> getCitiesByStateId(@PathVariable int stateId) {
        return citiesService.getCitiesByStateId(stateId);
    }
    
    

    
}
