package com.eschool;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.EventPermission;

@RestController
public class EventPermissionController {
	
	EventPermissionRepository eprepo;
	
	@PostMapping("saveEventPermission")
	public void save(@RequestBody EventPermission ep) {
		
		eprepo.save(ep);
	}
	
	

}
