package com.eschool;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Notification;
import com.eschool.beans.Otps;
import com.eschool.beans.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@RestController
public class NotificationController {
	void sendSMS(String pno,String otp)
	{
	try {
	String requestUrl  = "http://login.easywaysms.com/app/smsapi/index.php?key=364844DA3CBEC7&campaign=0&routeid=7&type=text&contacts="+pno+"&%20senderid=TOMKUM&msg=Dear%20User,%20Your%20OTP%20is%20"+otp+"%20for%20registration/AJAPAYOG.%20Valid%20for%2030%20minutes.%20Please%20do%20not%20share%20this%20OTP.%20Regards%20TOMKUM&template_id=1207169037651138885";
	URL url = new URL(requestUrl);
	HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	System.out.println(uc.getResponseMessage());
	uc.disconnect();
	} catch(Exception ex) {
	System.out.println("Error"+ex.getMessage());
	}
	}

	@Autowired
    private NotificationService emailService;
	@Autowired
	UserRepository urepo;
	
	private final OtpsService otpsService;

    @Autowired
    public NotificationController(OtpsService otpService) {
        this.otpsService = otpService;
    }
	
	@PostMapping("loginWithSmsOTP1/{pno}")
    public String loginWithSmsOTP1(@PathVariable String pno, HttpServletRequest request) {
    	
    	String message="";
    	
    	 // Create a Random object
        Random random = new Random();

        // Generate a random 4-digit OTP
        int number = 1000 + random.nextInt(9000);
        String otp = ""+number;
        
        
       Otps otp1=new Otps(0, number, pno);
        
       otpsService.saveOTP(otp1);
       
        System.out.println(otp);
       try {
    	   sendSMS(pno, otp);
    	   message="OTP Sent";
       }
       catch(Exception e) {
    	   message=e.getMessage();
       }
    	
    	return message;
    	
    }
    
    @PostMapping("verifySmsOTP1/{otp}/{pno}")
    public ResponseEntity<Object> verifySmsOTP1(@PathVariable String otp, @PathVariable String pno, HttpServletRequest request) {
    	String token_message = "";
		String type="";
		Map<String, String> data = new HashMap<>();

    	
    	System.out.println(pno);
    	System.out.println(otp);
    //	String otp1=request.getSession().getAttribute("otp").toString();
    	
    	int otp1= otpsService.getLatestOTPByPno(pno);
    	
    	
    	System.out.println(""+otp1);
    	if(otp.equals(""+otp1)) {
    		
    	 User u= urepo.findByMobileNum(pno);
    	 
    	 System.out.println(u.getFull_name());
    	 
    	 
    	 
    	 if (u != null && u.getStatus()==1) {
 			// The code to convert user information into JWT token
 			String token = Jwts.builder().claim("full_name", u.getFull_name()).claim("email", u.getEmail())
 					.claim("mobile_number", u.getMobileNum()).claim("id", u.getId()).claim("type", u.getUser_type())
 					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
 					.signWith(SignatureAlgorithm.HS256,"9wJYK7g67fTRC29iP6VnF89h5sW1rDcT3uXvA0qLmB4zE1pN8rS7zT0qF2eR5vJ3")
 					.compact();
 			type=u.getUser_type();
 			
 			token_message = token;
 			
 		} else {
 			token_message = "Invalid User information or user may be unapproved";
 		}
    		
    	}
    	
    	
    	data.put("token", token_message);
		data.put("type", type);
		return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
    
    
    
	
	
	

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
    
    @PostMapping("loginWithOTP/{email}")
    public String loginWithOTP(@PathVariable String email, HttpServletRequest request) {
    	
    	String message="";
    	
    	 // Create a Random object
        Random random = new Random();

        // Generate a random 4-digit OTP
        int number = 1000 + random.nextInt(9000);
        String otp = ""+number;
        
        
        
        request.getSession().setAttribute("otp", otp);
        
        
        try {
            // Assuming EmailRequest is a DTO containing 'receiver', 'subject', and 'body' fields
           emailService.sendEmail(email, "varification OTP", otp);
           message="Email sent successfully";
        	}
        	catch(Exception e) {
        		message=e.getMessage();
        	}
    	
    	return message;
    	
    }
    
    @PostMapping("verifyOTP/{otp}/{email}")
    public ResponseEntity<?> verifyOTP(@PathVariable String otp, @PathVariable String email, HttpServletRequest request) {
    	
    	System.out.println(email);
    	System.out.println(otp);
    	String otp1=request.getSession().getAttribute("otp").toString();

    	if(otp1 == request.getSession().getAttribute("otp")) {
    		
    	 User u= urepo.findByEmail(email);
    	 return ResponseEntity.ok(u);
    		
    	}
    	else {
    		return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("User not found with username: " + email);
    	}
    	
    	
    }
    
    
    @PostMapping("loginWithSmsOTP/{pno}")
    public String loginWithSmsOTP(@PathVariable String pno, HttpServletRequest request) {
    	
    	String message="";
    	
    	 // Create a Random object
        Random random = new Random();

        // Generate a random 4-digit OTP
        int number = 1000 + random.nextInt(9000);
        String otp = ""+number;
        
        
        HttpSession session=request.getSession();
        session.setMaxInactiveInterval(1800);
        session.setAttribute("otp", otp);
        
        System.out.println(otp);
       try {
    	   sendSMS(pno, otp);
    	   message="OTP Sent";
       }
       catch(Exception e) {
    	   message=e.getMessage();
       }
    	
    	return message;
    	
    }
    
    @PostMapping("verifySmsOTP/{otp}/{pno}")
    public ResponseEntity<?> verifySmsOTP(@PathVariable String otp, @PathVariable String pno, HttpServletRequest request) {
    	
    	System.out.println(pno);
    	System.out.println(otp);
    	String otp1=request.getSession().getAttribute("otp").toString();

    	if(otp.equals(otp1)) {
    		
    	 User u= urepo.findByMobileNum(pno);
    	 return ResponseEntity.ok(u);
    		
    	}
    	else {
    		return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(otp+" User not found with username: " + pno);
    	}
    	
    	
    }
    
    
    //-----------------------------------------------------------------------------------------------------------
    
    
}
