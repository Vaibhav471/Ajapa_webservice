package com.eschool;

import java.io.File;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.Part;

@RestController
public class EventController {
	
	
	@Autowired
	EventRepository erepo;
	
	@Autowired
	ServletContext context;
	
	@PersistenceContext
    private EntityManager entityManager; // Inject the EntityManager
	
	
	
	//--------------------------------------SAVE EVENT-------------------------------------------------------------------------------------
	
	
		@PostMapping("saveEvent")
		
		public ResponseEntity<Object> saveEvent(@RequestBody Event event) {
			
			
			String message = "";
			
			try {
			erepo.save(event);
			message="event created successfully";
			}
			catch(Exception e) {
				message=e.getMessage();		}
			
			Map<String, String> data = new HashMap();
	        data.put("token", message);
	        return new ResponseEntity<>(data, HttpStatus.OK);	
	        
			
	        }
		
		
		//-------------------------------------------TO EDIT EVENT----------------------------------------------------------
		
		@PutMapping("editEvent/{id}")
		public ResponseEntity<Object> editEvent(@PathVariable int id, @RequestBody Event event){
			
			String message="";
			
			Event existingEvent = erepo.findById(id);
			
			existingEvent.setEvent_location(event.getEvent_location());
			existingEvent.setEventName(event.getEventName());
			existingEvent.setEvent_type(event.getEvent_type());
			existingEvent.setEndDate(event.getEndDate());
			existingEvent.setListed_by(event.getListed_by());
			existingEvent.setStartDate(event.getStartDate());
			existingEvent.setEvent_status(event.getEvent_status());
			existingEvent.setOther(event.getOther());
			existingEvent.setStart_time(event.getStart_time());
			existingEvent.setEnd_time(event.getEnd_time());



			erepo.save(existingEvent);
			
			
			
			Map<String, String> data = new HashMap();
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
		public ResponseEntity<Object> saveEventDocument(@RequestParam("file") Part file, @RequestParam("event_id") int event_id) {
			
			String message="";
			
			
			String path=context.getRealPath("/")+"\\EventDoc";
			File fl=new File(path);
			if(!fl.exists())
			{
				fl.mkdir();
			}
			System.out.println("Image path "+path);
			Path root=Paths.get(path+"\\"+event_id+".jpg");
			
			
			
			try {
				
				if (Files.exists(root)) {
			        Files.delete(root); // Delete the existing image file
				}
				
				
				Files.copy(file.getInputStream(),root);
				message="document saved";
			} 
			
			
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.print(e.getMessage());
				
				message=e.getMessage();
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
		public List<Event> getUsers(@PathVariable int start, @PathVariable int end) {
		    List<Event> events = entityManager.createQuery("from Event", Event.class).getResultList();
		    
		    
		    
		    if (start >= 0 && end < events.size() && start <= end) {
		    	
		        return events.subList(start, end);
		    } 
		    else if (start >= 0 && start <= end) 
		    {
		    	return events.subList(start, events.size());
		    }
		    else  {
		        // Handle invalid start and end values, e.g., return an empty list or an error response.
		        return Collections.emptyList();
		    }
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	System.out.println(date);
	    	System.out.println(d1.toString());
	    	return erepo.findAllByStartDateGreaterThan(d1);
	    }

}
