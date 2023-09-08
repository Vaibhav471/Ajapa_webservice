package com.eschool;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Travel;

@RestController
public class TravelController {
	
	@Autowired
    TravelRepository trepo;
	
	@PostMapping("saveTravelDetails")
	public String saveTravelDetails(@RequestBody Travel travel) {
		
		String message="";
		try {
			trepo.save(travel);
			message = "Data saved successfully";			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		return message;
	}
	

}
