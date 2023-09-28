package com.eschool;


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

import com.eschool.beans.Travel;
import com.eschool.beans.TravelEventUser;

@RestController
public class TravelController {
	
	@Autowired
    TravelRepository trepo;
	@Autowired
	UserRepository urepo;
	@Autowired
	EventRepository erepo;
	private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }
	
	//-------------------------TO SAVE TRAVEL DETAILS---------------------------------------------------------------
	
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
	//-------------------------------GET ALL TRAVEL OBJECTS FROM DB--------------------------------------------------------------------
	@GetMapping("getAllTravels")
	public List<Travel> getAllTravels(){
		
		return trepo.findAll();
	}
	
	@GetMapping("getTravelById/{id}")
	Travel getTravelById(@PathVariable int id) {
		
		return trepo.findById(id);
	}
	
	@GetMapping("getTravelByUserId/{id}")
	public List<TravelEventUser> getTravelByUserId(@PathVariable int id) {
		
		List<Travel> t= trepo.findAllByUserId(id);
List<TravelEventUser> t1=new ArrayList();
		
		for(Travel tt:t) {
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravel_id());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromCity(tt.getFromCity());
			teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());
			teu.setUserName(urepo.findUserNameByUserId(tt.getUserId()));
			teu.setEventName(erepo.findEventNameByUserId(tt.getEventId()));

			t1.add(teu);

		}
		
		return t1;
		
		
	}
	
	@GetMapping("getAllTravel1")
	public List<TravelEventUser> getAllTravel1() {
		
		List<Travel> t= trepo.findAll();
		List<TravelEventUser> t1=new ArrayList();
		
		for(Travel tt:t) {
			TravelEventUser teu=new TravelEventUser();
			teu.setTravelId(tt.getTravel_id());
			teu.setEventId(tt.getEventId());
			teu.setUserId(tt.getUserId());
			teu.setFromCity(tt.getFromCity());
			teu.setFromCountry(tt.getFromCountry());
			teu.setArrivalDate(tt.getArrivalDate());
			teu.setArrivalTime(tt.getArrivalTime());
			teu.setArrivalModeOfTransport(tt.getArrivalModeOfTransport());
			teu.setArrivalTrainNumber(tt.getArrivalTrainNumber());
			teu.setArrivalTrainName(tt.getArrivalTrainName());
			teu.setDepartureDate(tt.getDepartureDate());
			teu.setDepartureTime(tt.getDepartureTime());
			teu.setDepartureModeOfTransport(tt.getDepartureModeOfTransport());
			teu.setDepartureTrainName(tt.getDepartureTrainName());
			teu.setDescription(tt.getDescription());
			teu.setUserName(urepo.findUserNameByUserId(tt.getUserId()));
			teu.setEventName(erepo.findEventNameByUserId(tt.getEventId()));

			t1.add(teu);

		}
		
		return t1;
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("getTravelByEventAndTravel/{eventId}/{familyId}")
	public List<Travel> getTravelByEventAndTravel(@PathVariable int eventId, @PathVariable int familyId){
		
		
		return trepo.findAllByEventIdAndFamilyId(eventId, familyId);
	}
	
	

}
