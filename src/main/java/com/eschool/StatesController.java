package com.eschool;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.States;

@RestController
public class StatesController {

	private final StatesService statesService;

    @Autowired
    public StatesController(StatesService statesService) {
        this.statesService = statesService;
    }
//----------------------------------------------------------------------------------------------
    @GetMapping("/states/{countryId}")
    public List<States> getStatesByCountryId(@PathVariable Long countryId) {
        return statesService.getStatesByCountryId(countryId);
    }
}
