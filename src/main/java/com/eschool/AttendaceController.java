package com.eschool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
		List<Travel> travels=trepo.findAllByEventIdOrderByFamilyId(eventId);
		
		for(Travel travel:travels)
		{
		User user=urepo.findById(travel.getUserId());
		Attendance attendance=arepo.findByUserIdAndEventId(travel.getUserId(),eventId);
		UserForAttendance userForAttendance;
		if(attendance!=null)		
			userForAttendance=new UserForAttendance(user, true,attendance.getHallNo());
		else
			userForAttendance=new UserForAttendance(user, false,"0");	
		users.add(userForAttendance);
		}
		return users;		
	}
	
	void sendRoomBookingSMS(String pno,String message)
	{
		try {
            String user = "AjapaYog";
            String pass = "123456";
            String sender = "ASTOVD";
            String phone = pno;
            String text = message+" is your one time password for login Ajapa Yog Sansthan - Team Astrovedha";
            String priority = "ndnd";
            String stype = "normal";

            // URL encode the text parameter
            String encodedText = URLEncoder.encode(text, "UTF-8");

            // Construct the URL
            String urlString = "https://trans.smsfresh.co/api/sendmsg.php?" +
                    "user=" + user +
                    "&pass=" + pass +
                    "&sender=" + sender +
                    "&phone=" + phone +
                    "&text=" + encodedText +
                    "&priority=" + priority +
                    "&stype=" + stype;
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            // Get the response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Print the response
            System.out.println("Response: " + response.toString());

            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	@PostMapping("saveAttendance")
	public ResponseEntity<Object> saveAttendance(@RequestBody AttendaceDetails attendaceDetails) {
	    int n=attendaceDetails.getEvents().size();
	    ArrayList<Integer> events=attendaceDetails.getEvents();
	    ArrayList<Integer> users=attendaceDetails.getUsers();
	    ArrayList<Boolean> isPresentList=attendaceDetails.getIsPresent();
	    ArrayList<String> hallNo=attendaceDetails.getHallNo();
	    for(int i=0;i<n;i++)
	    {
	    	Attendance attendance=arepo.findByUserIdAndEventId(users.get(i),events.get(i));
	    	if(attendance==null)
	    	{	   
	    		Attendance at=new Attendance(events.get(i),users.get(i),hallNo.get(i),isPresentList.get(i));
	    		arepo.save(at);	    		
	    	}
	    	else
	    	{
	    		attendance.setPresent(isPresentList.get(i));
	    		attendance.setHallNo(hallNo.get(i));
	    		arepo.save(attendance);
	    	}
	    }
	    Map<String, String> data = new HashMap<>();	
	    data.put("message", "Attendance marked");
		return new ResponseEntity<>(data, HttpStatus.OK);		
	}	
	@PostMapping("sendRoomBookingStatus")
	public ResponseEntity<Object> sendRoomBookingStatus(@RequestBody AttendaceDetails attendaceDetails) {
	    int n=attendaceDetails.getEvents().size();
	    ArrayList<Integer> events=attendaceDetails.getEvents();
	    ArrayList<Integer> users=attendaceDetails.getUsers();
	    ArrayList<Boolean> isPresentList=attendaceDetails.getIsPresent();
	    ArrayList<String> hallNo=attendaceDetails.getHallNo();
	    for(int i=0;i<n;i++)
	    {
	    	Attendance attendance=arepo.findByUserIdAndEventId(users.get(i),events.get(i));
	    	if(attendance==null)
	    	{	   
	    		Attendance at=new Attendance(events.get(i),users.get(i),hallNo.get(i),isPresentList.get(i));
	    		arepo.save(at);	    		
	    	}
	    	else
	    	{
	    		attendance.setPresent(isPresentList.get(i));
	    		attendance.setHallNo(hallNo.get(i));
	    		arepo.save(attendance);
	    	}
	      	int id=users.get(i);
	    	User user=urepo.findById(id);
	    	sendRoomBookingSMS(user.getMobileNum(),hallNo.get(i));
	    }
	    Map<String, String> data = new HashMap<>();	
	    data.put("message", "SMS Sent");
		return new ResponseEntity<>(data, HttpStatus.OK);		
	}
}
