package com.eschool;

import java.sql.Date;
import java.util.ArrayList;
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

import com.eschool.beans.Attendance;
import com.eschool.beans.Food;
import com.eschool.beans.Travel;
@RestController
public class FoodController {
	@Autowired
	TravelRepository trepo;
	@Autowired
	AttendanceRepository arepo;
	@Autowired
	FoodRepository frepo;	
	@GetMapping("getFoodDetail/{eventId}/{entryDate}/{timings}")
	Food getFoodDetail(@PathVariable int eventId,@PathVariable String entryDate,@PathVariable String timings) {	
	Food food=frepo.findByEventIdAndEntryDateAndTimings(eventId, entryDate, timings);
	if(food==null)
	{
		System.out.println("Hello1");
		List<Travel> registeredUsers=trepo.findAllByEventId(eventId);
		List<Attendance> presentUsers=arepo.findAllByEventId(eventId);
		food=new Food(0, eventId, entryDate, timings, presentUsers.size(), registeredUsers.size(),0);
	}	
	return food;
	}
	@GetMapping("getFoodDetails/{eventId}")
	List<Food> getFoodDetails(@PathVariable int eventId) {	
	List<Food> foods=frepo.findAllByEventId(eventId);			
	return foods;
	}
	
	@PostMapping("saveFoodDetails")
	public ResponseEntity<Object> saveFoodDetails(@RequestBody Food food) {	
	
		Food existingFood=frepo.findByFoodId(food.getFoodId());	
	if(existingFood==null)
	{
		frepo.save(food);
	}
	else
	{
		existingFood.setEventId(food.getEventId());
		existingFood.setEntryDate(food.getEntryDate());
		existingFood.setFoodTakenCount(food.getFoodTakenCount());
		existingFood.setPresentCount(food.getPresentCount());
		existingFood.setTimings(food.getTimings());
		existingFood.setTotalCount(food.getTotalCount());
		frepo.save(existingFood);		
	}
	Map<String, String> data = new HashMap<>();
	data.put("message","Data Saved");
	return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
}
