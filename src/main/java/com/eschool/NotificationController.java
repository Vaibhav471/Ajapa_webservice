package com.eschool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

import com.eschool.beans.EmailOTP;
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
	@Autowired
    private NotificationService emailService;
	@Autowired
	UserRepository urepo;	
	private final OtpsService otpsService;
	private final EmailOTPService emailotpService;

	/*void sendSMS(String pno,String otp)
	{
	try {
		System.out.println(otp);
	String requestUrl  = "http://login.easywaysms.com/app/smsapi/index.php?key=364844DA3CBEC7&campaign=0&routeid=7&type=text&contacts="+pno+"&%20senderid=TOMKUM&msg=Dear%20User,%20Your%20OTP%20is%20"+otp+"%20for%20registration/AJAPAYOG.%20Valid%20for%2030%20minutes.%20Please%20do%20not%20share%20this%20OTP.%20Regards%20TOMKUM&template_id=1207169037651138885";
	URL url = new URL(requestUrl);
	HttpURLConnection uc = (HttpURLConnection)url.openConnection();
	System.out.println(uc.getResponseMessage());
	uc.disconnect();
	} catch(Exception ex) {
	System.out.println("Error SMS API"+ex.getMessage());
	}
	}
*/
	void sendSMS(String pno,String otp)
	{
		try {
            String user = "freshtranss";
            String pass = "bulk@123";
            String sender = "SMSFRE";
            String phone = pno;
            String text = "Your OTP is :"+otp;
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
	
	public NotificationController(OtpsService otpService, EmailOTPService emailotpService) {
        this.otpsService = otpService;
        this.emailotpService = emailotpService;

    }
	@PostMapping("loginWithSmsOTP1/{pno}")
    public String loginWithSmsOTP1(@PathVariable String pno, HttpServletRequest request) {
     String message="";
     User user=urepo.findByEmailOrMobileNumber(pno);
     
     if(user==null)
     {
    	 message="Sorry... You are not a registered user";    		 
     }
     else
     {
    	 Random random = new Random(); 
    	 int number = 1000 + random.nextInt(9000);
         String otp = ""+number;
         Otps otp1=new Otps(0, number, pno);
         otpsService.saveOTP(otp1);
         try
         {
         if(pno.contains("@"))
        	 emailService.sendEmail(pno, "OTP verification ", otp);        	 
         else
        	 sendSMS(pno, otp);
         message="OTP Sent";
         }
         catch(Exception e)
         {
        	 message=e.getMessage();
         }         
     }     
     	return message;    	
    }
    
	
	@PostMapping("sendOtpEmailOrPno/{pno}/{email}")
    public String sendOtpEmailOrPno(@PathVariable String pno,@PathVariable String email) {
     String message="";
     Random random = new Random(); 
     int number = 1000 + random.nextInt(9000);
     String otp = ""+number;
     Otps otp1=new Otps(0, number, pno);
     Otps otp2=new Otps(1, number, email);     
     otpsService.saveOTP(otp1);
     otpsService.saveOTP(otp2);
     try
     {     
     emailService.sendEmail(email, "OTP verification ", otp);        	 
     sendSMS(pno, otp);
     message="OTP Sent";
     }
     catch(Exception e)
     {
     message=e.getMessage();
     }         
       
     	return message;    	
    }    
	@PostMapping("verifyEmailOrPno/{otp}/{pno}")
    public ResponseEntity<Object> verifyEmailOrPno(@PathVariable String otp, @PathVariable String pno){
    	String token_message = "";
		Map<String, String> data = new HashMap<>();		
	try {
    	int otp1= otpsService.getLatestOTPByPno(pno);
    	if(otp.equals(""+otp1)) {
    		token_message = "Valid OTP";    	
    		data.put("token", token_message);
    	}
    	else {
 			token_message = "Invalid OTP";
 			data.put("token", token_message);
 		}    	
    	}
    	catch(Exception e) {
    		token_message=e.getMessage();
    		data.put("token", token_message);    		
    	}
    	
		return new ResponseEntity<>(data, HttpStatus.OK);
    }

	
	
	
	
    @PostMapping("verifySmsOTP1/{otp}/{pno}")
    public ResponseEntity<Object> verifySmsOTP1(@PathVariable String otp, @PathVariable String pno){
    	String token_message = "";
		String type="";
		String isAdmin="";
		Map<String, String> data = new HashMap<>();
		
	try {
    	int otp1= otpsService.getLatestOTPByPno(pno);
    	if(otp.equals(""+otp1)) {
    	User u= urepo.findByEmailOrMobileNumber(pno);
    	if (u != null) {
    		 if(u.getStatus()!=1) {
    			 token_message="Unapproved User";
    			 data.put("token", token_message);
    		 }
    		 else {
 			// The code to convert user information into JWT token
 			String token = Jwts.builder().claim("fullName", u.getFullName()).claim("email", u.getEmail())
					.claim("mobileNumber", u.getMobileNum()).claim("id", u.getId()).claim("type", u.getUserType()).claim("familyId", u.getFamilyId())
					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
					.signWith(SignatureAlgorithm.HS256,"9wJYK7g67fTRC29iP6VnF89h5sW1rDcT3uXvA0qLmB4zE1pN8rS7zT0qF2eR5vJ3")
					.compact();
 			type=u.getUserType();
 			isAdmin=""+u.isAdmin();
 			token_message = token;
 			data.put("token", token_message);
 			data.put("type", type);
    		 }
    	 }
    	else {
 			token_message = "Invalid Pno/Email";
 			data.put("token", token_message);
 		}
    	}
    	else
    	{
    		token_message = "Invalid OTP";
 			data.put("token", token_message);
    	}
    	}
    	catch(Exception e) {
    		token_message="No user found";
    		data.put("token", token_message);    		
    	}
    	
		return new ResponseEntity<>(data, HttpStatus.OK);
    }
    
	
	
	//---------------------------------------------------------------------------------------------------------------------------------------------

    
 
    
    @PostMapping("loginWithOTP/{email}")
    public String loginWithOTP(@PathVariable String email, HttpServletRequest request) {
    	
    	String message="";
    	
    	 // Create a Random object
        Random random = new Random();

        // Generate a random 4-digit OTP
        int number = 1000 + random.nextInt(9000);
        String otp = ""+number;     
        
        request.getSession().setAttribute("otp", otp);
        
        EmailOTP emailOTP=new EmailOTP(0,number,email);
        emailotpService.saveOTP(emailOTP);
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
    	String token_message = "";
		String type="";
		Map<String, String> data = new HashMap<>();
    	int otp1= emailotpService.getLatestOTPByEmail(email);
       	if(otp.equals(""+otp1)) {    		
    	 User u= urepo.findByEmail(email);
     if (u != null) {
    		 if(u.getStatus()!=1) {
    			 token_message="Unapproved User";
    			 data.put("token", token_message);    				
    		 }
    		 else {
 			// The code to convert user information into JWT token
 			String token = Jwts.builder().claim("fullName", u.getFullName()).claim("email", u.getEmail())
					.claim("mobileNumber", u.getMobileNum()).claim("id", u.getId()).claim("type", u.getUserType()).claim("familyId", u.getFamilyId())
					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
					.signWith(SignatureAlgorithm.HS256,"9wJYK7g67fTRC29iP6VnF89h5sW1rDcT3uXvA0qLmB4zE1pN8rS7zT0qF2eR5vJ3")
					.compact();
 			type=u.getUserType(); 			
 			token_message = token;
 			data.put("token", token_message);
 			data.put("type", type);
    		 }   				
    	 }
    	 else {
 			token_message = "Invalid User information";
 			data.put("token", token_message); 			
 		}   	
    
    	}    	
		return new ResponseEntity<>(data, HttpStatus.OK);    	
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
