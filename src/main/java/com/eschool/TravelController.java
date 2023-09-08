package com.eschool;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Travel;

@RestController
public class TravelController {
	
	@Autowired
    TravelRepository trepo;
	
	@PostMapping("saveTravelDetails")
	public ResponseEntity<Object> saveTravelDetails(@RequestBody Travel travel) {
		Map<String, String> data = new HashMap<>();

		String message="";
		try {
			trepo.save(travel);
			message = "Data saved successfully";			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		data.put("msg", message);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	

}
