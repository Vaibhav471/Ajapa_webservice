package com.eschool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Notification;



@RestController
public class NotificationController {
	
	@Autowired
    private NotificationService emailService;

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody Notification notification) {
    	
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

}
