package com.eschool;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.eschool.beans.Event;
import com.eschool.beans.EventPermission;
import com.eschool.beans.EventWithPermission;

@RestController
public class EventPermissionController {
	@Autowired
	EventPermissionRepository eprepo;	
	@Autowired
	EventRepository erepo;	
	@PostMapping("saveEventPermission")
	public String save(@RequestBody EventPermission ep) {
		String message;		
		EventPermission e=eprepo.findByAdminIdAndEventId(ep.getAdminId(), ep.getEventId());
		if(e==null) {
		try {
		eprepo.save(ep);
		message="Entity Saved";
		}
		catch(Exception ee) {
			message=ee.getMessage();			
		}
		}
		else {
			e.setCanDelete(ep.getCanDelete());
			e.setCanModify(ep.getCanModify());			
			eprepo.save(e);
			message="Event Permissions Updated";			
		}
		
		return message;
	}
	//-------------------------------------------------------------------------------------------------------------------
	@GetMapping("getEventWithPermissions/{adminId}")
	public List<EventWithPermission> getEventWithPermission(@PathVariable String adminId){
		
		ArrayList<EventWithPermission> ewp=new ArrayList<EventWithPermission>();
		List<EventPermission> ep=eprepo.findByAdminId(adminId);
		
		for(EventPermission e:ep) {			
			Event ev=erepo.findById(e.getEventId());
			EventWithPermission ee= new EventWithPermission();
			ee.setEventId(ev.getEventId());
			ee.setEventName(ev.getEventName());
			ee.setEventType(ev.getEventType());
			ee.setEventLocation(ev.getEventLocation());
			ee.setListedBy(ev.getListedBy());
			ee.setAdminId(e.getAdminId());
			ee.setStartTime(ev.getStartTime());
			ee.setEndTime(ev.getEndTime());
			ee.setEventStatus(ev.getEventStatus());
			ee.setStartDate(ev.getStartDate());
			ee.setEndDate(ev.getEndDate());
			ee.setLockArrivalDate(ev.getLockArrivalDate());
			ee.setLockDepartureDate(ev.getLockDepartureDate());
			ee.setCanDelete(e.getCanDelete());
			ee.setCanModify(e.getCanModify());
			
			ewp.add(ee);			 
		}
		
		return ewp;
	}
	
//---------------------------------------------------------------------------------------------------------------
	@GetMapping("getAllEventsWitPermissions")
	public List<EventWithPermission> getAllEventsWitPermissions(){		
		ArrayList<EventWithPermission> ewp=new java.util.ArrayList<>();
		List<EventPermission> ep=eprepo.findAll();		
		for(EventPermission e:ep) {			
			Event ev=erepo.findById(e.getEventId());
			EventWithPermission ee = new EventWithPermission();
			ee.setEventId(ev.getEventId());
			ee.setEventName(ev.getEventName());
			ee.setEventType(ev.getEventType());
			ee.setEventLocation(ev.getEventLocation());
			ee.setListedBy(ev.getListedBy());
			ee.setAdminId(e.getAdminId());
			ee.setStartTime(ev.getStartTime());
			ee.setEndTime(ev.getEndTime());
			ee.setEventStatus(ev.getEventStatus());
			ee.setStartDate(ev.getStartDate());
			ee.setEndDate(ev.getEndDate());
			ee.setLockArrivalDate(ev.getLockArrivalDate());
			ee.setLockDepartureDate(ev.getLockDepartureDate());
			ee.setCanDelete(e.getCanDelete());
			ee.setCanModify(e.getCanModify());			
			ewp.add(ee);			 
		}		
		return ewp;	
		}
}
