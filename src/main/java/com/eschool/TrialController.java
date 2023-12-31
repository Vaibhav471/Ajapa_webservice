package com.eschool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Trial;
import com.eschool.beans.User;

@RestController
public class TrialController {
	@Autowired
	TrialRepository trepo;	
	@PostMapping("saveTrial")
	public ResponseEntity<Object> saveTrial(@RequestBody Trial trial) {
	String	message="";
		Map<String, String> data = new HashMap<>();	
		
		try {			
			trepo.save(trial);
			message = "Data saved successfully";
					
			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		data.put("msg", message);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	@GetMapping("getTrials")
	public List<Trial> getTrials() {
		List<Trial> trials=trepo.findAll();
		return trials;
		
	}
}
