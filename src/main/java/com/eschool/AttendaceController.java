package com.eschool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.eschool.beans.AttendaceDetails;
import com.eschool.beans.Attendance;
import com.eschool.beans.Event;
import com.eschool.beans.Travel;
import com.eschool.beans.User;
import com.eschool.beans.UserForAttendance;

@RestController
public class AttendaceController {

	@Autowired
	AttendanceRepository arepo;
	@Autowired
	TravelRepository trepo;
	@Autowired
	UserRepository urepo;
	@GetMapping("fetchRegisteredUserByEvent/{eventId}")
	public List<UserForAttendance> fetchRegisteredUserByEvent(@PathVariable int eventId) {
	    List<UserForAttendance> users=new ArrayList<UserForAttendance>();
		List<Travel> travels=trepo.findAllByEventId(eventId);
		for(Travel travel:travels)
		{
		User user=urepo.findById(travel.getUserId());
		Attendance attendance=arepo.findByUserIdAndEventId(travel.getUserId(),eventId);
		UserForAttendance userForAttendance;
		if(attendance!=null)		
			userForAttendance=new UserForAttendance(user, true);
		else
			userForAttendance=new UserForAttendance(user, false);	
		users.add(userForAttendance);
		}
		return users;		
	}
	
	@PostMapping("saveAttendance")
	public ResponseEntity<Object> saveAttendance(@RequestBody AttendaceDetails attendaceDetails) {
	    int n=attendaceDetails.getEvents().size();
	   ArrayList<Integer> events=attendaceDetails.getEvents();
	    ArrayList<Integer> users=attendaceDetails.getUsers();
	    ArrayList<Boolean> isPresentList=attendaceDetails.getIsPresent();
	    for(int i=0;i<n;i++)
	    {
	    	Attendance attendance=arepo.findByUserIdAndEventId(users.get(i),events.get(i));
	    	if(attendance==null)
	    	{
	   
	    		if(isPresentList.get(i)==true)
	    		{
	    		Attendance at=new Attendance(events.get(i),users.get(i));
	    		arepo.save(at);
	    		}
	    	}
	    	else
	    	{
	    		if(isPresentList.get(i)==false)
	    		{
	    			arepo.delete(attendance);
	    		}
	    	}
	    }
	    Map<String, String> data = new HashMap<>();	
	    data.put("message", "Attendance marked");
		return new ResponseEntity<>(data, HttpStatus.OK);		
	}
	
	@PostMapping("getAttendance")
	public AttendaceDetails getAttendance() {
	    ArrayList<Integer> events=new ArrayList<>();
	    ArrayList<Integer> users=new ArrayList<>();
	    ArrayList<Boolean> isPresentList=new ArrayList<>();
	    events.add(137);
	    events.add(137);
	    events.add(137);
	    users.add(199);
	    users.add(200);
	    users.add(202);
	    isPresentList.add(false);
	    isPresentList.add(false);
	    isPresentList.add(false);
	    AttendaceDetails a=new AttendaceDetails(events, users, isPresentList);
	    return a;    
	}
	
	
	
}
