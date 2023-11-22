package com.eschool;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Event;
import com.eschool.beans.Notification;
import com.eschool.beans.Travel;
import com.eschool.beans.User;

import jakarta.mail.Multipart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

@RestController
public class EventController {	
	@Autowired
	EventRepository erepo;	
	@Autowired
	EventPermissionRepository eprepo;
	
	@Autowired
	ServletContext context;
	
	@Autowired
    TravelRepository trepo;
	
	@Autowired
	UserRepository urepo;
	
	@PersistenceContext
    private EntityManager entityManager; // Inject the EntityManager
	
	@Autowired
    private NotificationService emailService;
	
public String sendEmail(Notification notification) {
    	
    	String message="";
    	
    	try {
        // Assuming EmailRequest is a DTO containing 'to', 'subject', and 'body' fields
       emailService.sendEmail(notification.getReceiver(), notification.getSubject(), notification.getBody());
       message="Email sent successfully";
    	}
    	catch(Exception e) {
    		message=e.getMessage();
    	}
       return message;
    }

public int getEventIdByEmail(String email) {

	String message="";
	int eventId=0;
	
	try {
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/ajapa?user=root&password=root");
	
	 // Prepare the SQL query
    String query = "SELECT MAX(event_id) as event_id FROM event WHERE listed_by = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);

	preparedStatement.setString(1,email);

    // Execute the query
    ResultSet resultSet = preparedStatement.executeQuery();

    // Process the result
    if (resultSet.next()) {
         eventId = resultSet.getInt("event_id");
       
    } else {
        System.out.println("No events found.");
    }

    // Close resources
    resultSet.close();
    preparedStatement.close();
    connection.close();
} 
	
	catch (Exception e) {
    e.printStackTrace();
    System.out.println(e.getMessage());
}			
	return eventId;
	
}



void sendSMS(String pno,String msg)
{
try {
String requestUrl  = "http://login.easywaysms.com/app/smsapi/index.php?key=364844DA3CBEC7&campaign=0&routeid=7&type=text&contacts="+pno+"&%20senderid=TOMKUM&msg="+msg;
URL url = new URL(requestUrl);
HttpURLConnection uc = (HttpURLConnection)url.openConnection();
System.out.println(uc.getResponseMessage());
uc.disconnect();
} catch(Exception ex) {
System.out.println("Error"+ex.getMessage());
}
}
	
	
	
	//--------------------------------------SAVE EVENT-------------------------------------------------------------------------------------
	
	
		@PostMapping("saveEvent")
		public ResponseEntity<Object> saveEvent(@RequestBody Event event) {			
			String message = "";			
			try {
		    erepo.save(event);	    
			message="event created successfully";
			System.out.println("Check"+event.getShivirAvailable());
			}
			catch(Exception e) {
				message=e.getMessage();		
				}			
			System.out.println(event.getListedBy());
			int eventId=getEventIdByEmail(event.getListedBy());			
			Map<String, String> data = new HashMap<>();
	        data.put("token", message);
	        data.put("eventId",""+eventId);
	        return new ResponseEntity<>(data, HttpStatus.OK);	        
	        }
		
		
		//-------------------------------------------TO EDIT EVENT----------------------------------------------------------
		
		@PutMapping("editEvent/{id}")
		public ResponseEntity<Object> editEvent(@PathVariable int id, @RequestBody Event event){
			
			String message="";			
			Event existingEvent = erepo.findById(id);			
			existingEvent.setEventLocation(event.getEventLocation());
			existingEvent.setEventName(event.getEventName());
			existingEvent.setEventType(event.getEventType());
			existingEvent.setEndDate(event.getEndDate());
			existingEvent.setListedBy(event.getListedBy());
			existingEvent.setStartDate(event.getStartDate());
			existingEvent.setEventStatus(event.getEventStatus());
			//existingEvent.setOther(event.getOther());
			existingEvent.setLockArrivalDate(event.getLockArrivalDate());
			existingEvent.setLockDepartureDate(event.getLockDepartureDate());
			existingEvent.setStartTime(event.getStartTime());
			existingEvent.setEndTime(event.getEndTime());
			erepo.save(existingEvent);				
			Map<String, String> data = new HashMap<>();
	        data.put("token", message);
	         return new ResponseEntity<>(data, HttpStatus.OK);
			
		}
		
		//-----------------------------FETCH EVENT INFO----------------------------------------------------------------------------------------------------------

		@GetMapping("fetchEvent/{ids}")
		public Event fetchEvent( @PathVariable String ids) {			
			int id=Integer.parseInt(ids);			
			Event event= erepo.findById(id);
			return event;		
		}
		
		//------------------------------TO SAVE EVENT RELATED DOCUMENTS-------------------------------------------------------------------------------------------------------------
		
		@PostMapping("saveEventD")
		public ResponseEntity<Object> saveEventDocument(@RequestParam("file") Part file, @RequestParam("eventId") int eventId) {
			String message="";
			if(file!=null)
			{			
			String path=context.getRealPath("/")+"\\EventDoc";
			File fl=new File(path);
			if(!fl.exists())
			{
				fl.mkdir();
			}
			System.out.println("Image path "+path);
			Path root=Paths.get(path+"\\"+eventId+".jpg");			
			try {
				if (Files.exists(root)) {
			        Files.delete(root); // Delete the existing image file
				}				
				Files.copy(file.getInputStream(),root);
				
			} 	
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print("Error 1"+e.getMessage());				
				message=e.getMessage();
			}
			}
			Map<String, String> data = new HashMap();
		    data.put("message", message);
		     return new ResponseEntity<>(data, HttpStatus.OK);		
			}	
		//--------------------------------------TO GET LAST EVENT'S ID BY EMAIL OF ADMIN------------------------------------------------------------------------
		@GetMapping("getEventId/{email}")
		public ResponseEntity<Object> getEventId(@PathVariable("email")String email){
			
			String message="";
			int eventId=0;
			
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection("jdbc:mysql://localhost/ajapa?user=root&password=root");
			
			 // Prepare the SQL query
	        String query = "SELECT MAX(event_id) as event_id FROM event WHERE listed_by = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1,email);

	        // Execute the query
	        ResultSet resultSet = preparedStatement.executeQuery();

	        // Process the result
	        if (resultSet.next()) {
	             eventId = resultSet.getInt("event_id");
	           
	        } else {
	            System.out.println("No events found.");
	        }

	        // Close resources
	        resultSet.close();
	        preparedStatement.close();
	        connection.close();
	    } 
			
			catch (Exception e) {
	        e.printStackTrace();
	    }			
			Map<String, Integer> data = new HashMap();
		    data.put("message", eventId);
		     return new ResponseEntity<>(data, HttpStatus.OK);
		}
		
		//------------------------------TO GET ALL EVENTS IN A PAGINATION FORMAT-------------------------------------------------------------
		@GetMapping("getEvents/{start}/{end}")
		public ResponseEntity<Object> getEvents(@PathVariable int start, @PathVariable int end) {
			List<Event> myEvents=null;
			List<Event> events=null;
			try
		   {
			 //events= erepo.findAll();
			 events= erepo.findAllByOrderByStartDateDesc();
			 
		    if (start >= 0 && end < events.size() && start <= end) {
		        myEvents=events.subList(start-1, end);
		    } 
		    else if (start >= 0 && start <= end) 
		    {
		    	myEvents=events.subList(start-1, events.size());
		    }
		    else  {
		    	myEvents=Collections.emptyList();
		    }
		   }
		   catch(Exception e)
		   {
			   myEvents=Collections.emptyList();
		   }
			Map<String, Object> data = new HashMap<>();
		    data.put("data", myEvents);
		    data.put("size", events.size());		    
		    return new ResponseEntity<>(data, HttpStatus.OK);
		}
		
		@GetMapping("getEventsByStatus/{status}/{start}/{end}")
		public ResponseEntity<Object> getEventsByStatus(@PathVariable int status,@PathVariable int start, @PathVariable int end) {
			List<Event> myEvents=null;
			List<Event> events=null;
			try
		   {
			 //events= erepo.findAllByEventStatus(status);
		     events= erepo.findAllByEventStatusOrderByStartDateDesc(status);
		    if (start >= 0 && end < events.size() && start <= end) {
		        myEvents=events.subList(start-1, end);
		    } 
		    else if (start >= 0 && start <= end) 
		    {
		    	myEvents=events.subList(start-1, events.size());
		    }
		    else  {
		    	myEvents=Collections.emptyList();
		    }
		   }
		   catch(Exception e)
		   {
			   myEvents=Collections.emptyList();
		   }
			Map<String, Object> data = new HashMap<>();
		    data.put("data", myEvents);
		    data.put("size", events.size());		    
		    return new ResponseEntity<>(data, HttpStatus.OK);
		}
		//----------------------------------------------------------------------------------------------------------------------------------------------
	    @GetMapping("getNumberOfEvents")
	    public long getNumerOfEvents() {	    	
	    	return erepo.count();
	    }
	    
	    //---------------------------------------------------------------------------------------------------------------------------------------------------
	    @GetMapping("getEventsGreaterThan/{date}")
	    public List<Event> getEventsGreaterThan(@PathVariable String date){
	    	 java.util.Date d1=null;
			try {
				java.text.SimpleDateFormat sd=new java.text.SimpleDateFormat("yyyy-MM-dd");
				d1 = sd.parse(date);
			} catch (ParseException e) {
				System.out.println("Error:"+e.getMessage());
				e.printStackTrace();
			}
	     	return erepo.findAllByStartDateGreaterThan(d1);
	    }
	    //-------------------------------------------------------------------------------------------------------------------------------------------------------------
	    @GetMapping("getAllEvents")
	    public List<Event> getAllEvents(){	    	
	    	return erepo.findAll();
	    }
	    //----------------------------------------------------------------------------------------------------------------
	    @GetMapping("getAllActiveEvents")
	    public List<Event> getAllActiveEvents(){
	    	return erepo.findAllByOrderByStartDateDesc();
	    }
	    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	    
	    @PostMapping("changeBookingStatus/{eventId}/{status}")
	    public String changeBookingStatus(@PathVariable int eventId,@PathVariable int status) {
	    	String message="";    	
	    	try {
	    	Event e=erepo.findById(eventId);
	    	e.setBookingStatus(status);
	    	erepo.save(e);
	    	message="Event Booking Status Changed";
	    	}
	    	catch(Exception e) {
	    		message=e.getMessage();
	    	}
	    	return message;
	    }
	    
	    
	    @PostMapping("changeEventStatus/{eventId}/{status}")
	    public String changeEventStatus(@PathVariable int eventId,@PathVariable int status) {
	    	String message="";    	
	    	try {
	    	Event e=erepo.findById(eventId);
	    	e.setEventStatus(status);
	    	erepo.save(e);
	    	message="Event Status Changed";
	    	}
	    	catch(Exception e) {
	    		message=e.getMessage();
	    	}
	    	return message;
	    }
	    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	    @PostMapping("sendSmsByEventId/{eventId}")
	    public String sendSmsByEventId(@PathVariable int eventId, @RequestBody String message) {
	    	String message1="";	    	
	    	List<Travel> travel=trepo.findAllByEventId(eventId);
	    	for(Travel t:travel) {
	    		System.out.println(t.getUserId());	
	    		User user=urepo.findById(t.getUserId());
	    	//sendSMS(user.getMobileNum(),message);
	    	}
	    	return message1;
	    }
	    //----------------------------------------------------------------------------------------------------------------------
}
