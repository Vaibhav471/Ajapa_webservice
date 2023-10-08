package com.eschool;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eschool.beans.Notification;
import com.eschool.beans.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletContext;

@RestController
public class UserController {
	String message;
	@Autowired
	UserRepository urepo;
	@Autowired
	ServletContext context;
	@PersistenceContext
	private EntityManager entityManager;
	private final UserService userService;
	@Autowired
    private NotificationService emailService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
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

	@PostMapping("signup")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		message="";
		Map<String, String> data = new HashMap<>();
		
		
		
		
		try {
			
			if(urepo.findByEmail(user.getEmail())!=null) {
				message="User exists";
			}
			else if(urepo.findByMobileNum(user.getMobileNum())!=null) {
				message="User exists";

			}
			
			else {
			urepo.save(user);
			int id1 = urepo.findIdByEmail(user.getEmail());
			System.out.println(id1);
			User u= urepo.findByEmail(user.getEmail());
			u.setFamilyId(id1);
			urepo.save(u);
			message = "Data saved successfully";
			}
			
			
			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		data.put("msg", message);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	@PostMapping("signup2")
	public ResponseEntity<Object> saveUser2(@RequestBody User user, @RequestHeader("Authorization") String authorizationHeader) {
		message="";
		Map<String, String> data = new HashMap<>();
		
		int id=0;
		//System.out.println(authorizationHeader);// token from header displayed on console The code to decode the JWT token sent by client
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String jwtToken = authorizationHeader.substring(7); // Removing "Bearer " prefix
			//System.out.println(jwtToken);
			try {
				Base64.Decoder decoder = Base64.getUrlDecoder();
				String chunks[] = jwtToken.split("\\.");
				String header = new String(decoder.decode(chunks[0]));
				String payload = new String(decoder.decode(chunks[1]));
				System.out.println(header);
				System.out.println(payload);
				ObjectMapper mapper = new ObjectMapper();
				Map<String, Object> map = mapper.readValue(payload, Map.class);
				System.out.println("this is our email");
				System.out.println(map.get("email"));
				System.out.println(map.get("id"));
				id=Integer.parseInt(map.get("familyId").toString());
				System.out.println("our id is "+id);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to decode JWT: " + e.getMessage());
			}
		}		
		
		try {
			if(urepo.findByEmail(user.getEmail())!=null) {
				message="User exists";
			}
			else {
			user.setFamilyId(id);
			
			urepo.save(user);
			message = "Data saved successfully";
			
			}
			
		} catch (Exception e) {
			message = e.getMessage();
		}		
		data.put("msg", message);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	// --------------------------------LOGIN------------------------------------------------------------------------------------------------------
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		String token_message = "";
		String type="";
		String isAdmin="";

		Map<String, String> data = new HashMap<>();
		try
		{
		 String identifier = user.getIdentifier();
        String password = user.getPassword();
        User u = userService.getUserByEmailOrMobileNumberAndPassword(identifier, password);
        System.out.println("this is our user");
        System.out.println(u);
		
		if (u != null) {
			if(u.getStatus()!=1) {
				token_message="Unapproved User";
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
			}
		}
		else {
			token_message = "Invalid User information";
		}
		
		}
		catch(Exception e)
		{
			token_message=e.getMessage();
		}
		data.put("token", token_message);
		data.put("type", type);
		data.put("isAdmin",isAdmin );

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// ---------------------------------UPDATE USER----------------------------------------------------------------------------------------------------------------------
	@PutMapping("updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody User user,@RequestHeader("Authorization") String authorizationHeader) {
		String message = "";
		String email = "";
		int id;
		//System.out.println(authorizationHeader);// token from header displayed on console The code to decode the JWT token sent by client
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String jwtToken = authorizationHeader.substring(7); // Removing "Bearer " prefix
			//System.out.println(jwtToken);
			try {
				Base64.Decoder decoder = Base64.getUrlDecoder();
				String chunks[] = jwtToken.split("\\.");
				String header = new String(decoder.decode(chunks[0]));
				String payload = new String(decoder.decode(chunks[1]));
				System.out.println(header);
				System.out.println(payload);
				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = mapper.readValue(payload, Map.class);
				System.out.println("this is our email");
				System.out.println(map.get("email"));
				email = map.get("email"); // getting email from token in a string variable

				id=Integer.parseInt(map.get("id"));
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to decode JWT: " + e.getMessage());
			}
		}
		User existingUser = urepo.findByEmail(email);
		if (existingUser != null) {
			if (user.getFullName() != null) {
				existingUser.setFullName(user.getFullName()); // this is a mandatory field, if client sends it null at the time of updation, we do not set null as fiel value.
			}
			if (user.getDob() != null) {
				existingUser.setDob(user.getDob());
			}
			if (user.getMobileNum() != null) {
				existingUser.setMobileNum(user.getMobileNum());
			}
			if (user.getGender() != null) {
				existingUser.setGender(user.getGender());
			}
			existingUser.setWhatsappNum(user.getWhatsappNum());
			if (user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}
			existingUser.setBloodGrp(user.getBloodGrp());
			existingUser.setOccupation(user.getOccupation());
			existingUser.setQualification(user.getQualification());
			existingUser.setAddressLinep(user.getAddressLinep());
			existingUser.setAddressLines(user.getAddressLines());
			if (user.getCountry() != null) {
				existingUser.setCountry(user.getCountry());
			}
			existingUser.setState(user.getState());
			if (user.getCity() != null) {
				existingUser.setCity(user.getCity());
			}

			existingUser.setDikshaDt(user.getDikshaDt());
			existingUser.setAge(user.getAge());
			existingUser.setPincode(user.getPincode());

			urepo.save(existingUser);

			message = "user updated";
			
			Notification n=new Notification();
			n.setReceiver(email);
			n.setSubject("update information");
			n.setBody("your profile has been updated successfully");
			
			
			sendEmail(n);
		}

		else {
			throw new IllegalArgumentException("User not found");

		}

		Map<String, String> data = new HashMap();
		data.put("token", message);
		return new ResponseEntity<>(data, HttpStatus.OK);

	}

	// -------------------TO APPROVE OR REJECT A USER'S APPLICATION TO SIGNUP----------------------------------------------------------

	@PostMapping("changeStatus/{email}")
	public ResponseEntity<Object> changeStatus(@PathVariable String email) {

		String message = "";

		User existingUser = urepo.findByEmail(email);

		if (existingUser != null) {

			existingUser.setStatus(1);
			urepo.save(existingUser);
			message="User Approved";
		}
		Map<String, String> data = new HashMap();
		data.put("token", message);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// ----------------------------TO SAVE A USER'S IMAGE----------------------------------------------------------------------------------------------------------------

	@PostMapping("saveImage")
	public ResponseEntity<Object> saveImage(@RequestParam("file") MultipartFile file, @RequestHeader("Authorization") String authorizationHeader) {

		String email = "";
		String message = "";

		// The code to decode the JWT token sent by client
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String jwtToken = authorizationHeader.substring(7); // Removing "Bearer " prefix

			System.out.println(jwtToken);

			try {

				Base64.Decoder decoder = Base64.getUrlDecoder();
				String chunks[] = jwtToken.split("\\.");

				String header = new String(decoder.decode(chunks[0]));
				String payload = new String(decoder.decode(chunks[1]));

				System.out.println(header);

				System.out.println(payload);
				ObjectMapper mapper = new ObjectMapper();
				Map<String, String> map = mapper.readValue(payload, Map.class);

				System.out.println("this is our email");
				System.out.println(map.get("email"));

				email = map.get("email"); // getting email from token in a string variable

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to decode JWT: " + e.getMessage());
				message = e.getMessage();
			}
		}

		// String imageName=new java.util.Date().toString()+".jpg";
		String path = context.getRealPath("/") + "\\images";
		File fl = new File(path);
		if (!fl.exists()) {
			fl.mkdir();
		}
		System.out.println("Image path " + path);
		Path root = Paths.get(path + "\\" + email + ".jpg");

		try {

			if (Files.exists(root)) {
				Files.delete(root); // Delete the existing image file
			}

			Files.copy(file.getInputStream(), root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.print(e.getMessage());

			message = e.getMessage();
		}

		Map<String, String> data = new HashMap();
		data.put("message", message);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}
	
	
	//------------------------------TO GET ALL USERS IN A PAGINATION FORMAT-------------------------------------------------------------
	
	@GetMapping("getUsers/{start}/{end}")
	public List<User> getUsers(@PathVariable int start, @PathVariable int end) {
	    List<User> users = entityManager.createQuery("from User", User.class).getResultList();
	    
	    if (start >= 0 && end <= users.size() && start <= end) {
	        return users.subList(start, end);
	    } else {
	        // Handle invalid start and end values, e.g., return an empty list or an error response.
	        return Collections.emptyList();
	    }
	}
	
	// ----------------------------TO GET EMAIL ADDRESS----------------------------------------------------------------------------------------------------------------

		@PostMapping("getEmail")
		public ResponseEntity<Object> getEmail(@RequestHeader("Authorization") String authorizationHeader) {

			String email = "";
			
			System.out.println(authorizationHeader);

			// The code to decode the JWT token sent by client
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				String jwtToken = authorizationHeader.substring(7); // Removing "Bearer " prefix

				System.out.println(jwtToken);

				try {

					Base64.Decoder decoder = Base64.getUrlDecoder();
					String chunks[] = jwtToken.split("\\.");

					String header = new String(decoder.decode(chunks[0]));
					String payload = new String(decoder.decode(chunks[1]));

					System.out.println(header);

					System.out.println(payload);
					ObjectMapper mapper = new ObjectMapper();
					Map<String, String> map = mapper.readValue(payload, Map.class);

					System.out.println("this is our email");
					System.out.println(map.get("email"));

					email = map.get("email"); // getting email from token in a string variable

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("Failed to decode JWT: " + e.getMessage());
					message = e.getMessage();
				}
			}

			
			Map<String, String> data = new HashMap();
			data.put("Email", email);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		
	//---------------------------------TO GET LIST OF UNAPPROVED USERS------------------------------------------------------------------------------	
		@GetMapping("getUsersToApprove")
		public List<User> getUsersToApprove(){
			
			return urepo.findUsersByStatus(0);
		}
		
	//-------------------------------TO GET USER DETAILS BY IT'S EMAIL----------------------------------------------------------------------	
		@GetMapping("getUserDetails/{email}")
		public User getUserDetails(@PathVariable String email) {
			
			return urepo.findByEmail(email);
		}
	//-------------------------------------TO GET ALL USERS FROM DB--------------------------------------------------	
		@GetMapping("getAllUsers")
		public List<User> getAllUsers(){
			
			return urepo.findAll();
		}
	//----------------------------TO GET USER DETAILS BY ID--------------------------------------------------------------------
		@GetMapping("getUserById/{id}")
		public User getUserById(@PathVariable int id) {
			
			return urepo.findById(id);
		}
	//----------------------------------------------------------------------------------------------------
		@GetMapping("getNumberOfUnapprovedUsers")
		public long getNumberOfUnapprovedUsers() {
			
			return urepo.countByStatus(0);
			
		}
		
		//----------------------------------------------------------------------------------------------------
				@GetMapping("getFamilyMembers/{family_id}")
				public List<User> getFamilyMembers(@PathVariable int family_id) {
					
					List<User> u=urepo.findUsersByFamilyId(family_id);
					List<User> uu=new ArrayList<>();
					for(User u1:u) {
						if(u1.getStatus()==1) {
							uu.add(u1);
						}
					}
					return uu;
				}
				
	//----------------------------------------------------------------------------------------------------------------------------------			
				@PostMapping("rejectUser/{email}")
				public ResponseEntity<Object> rejectUser(@PathVariable String email) {

					String message = "";

					User existingUser = urepo.findByEmail(email);

					if (existingUser != null) {

						existingUser.setStatus(2);
						urepo.save(existingUser);
						message="User deleted";
					}
					Map<String, String> data = new HashMap();
					data.put("token", message);
					return new ResponseEntity<>(data, HttpStatus.OK);
				}
	//			-------------------------------------------------------------------------------------------------
				@GetMapping("getRejectedUsers")
				public List<User> getRejectedUsers(){
					
					return urepo.findUsersByStatus(2);
				}
				
				//------------------------------------------------------------------------------------------------------
				@PostMapping("makeAdmin/{id}")
				public void makeAdmin(@PathVariable int id) {
					
					User u=urepo.findById(id);
					u.setAdmin(true);
					
					urepo.save(u);
					
				}
				
				//----------------------------------------------------------------------------------------------------------------------------------
				@GetMapping("getApprovedUsers")
				public List<User> getApprovedUsers(){
					
					return urepo.findUsersByStatus(1);
				}
	//---------------------------------------------------------------------------------------------------------------------------------------
				@GetMapping("getAllAdmins")
				public List<User> getAllAdmins(){
					return urepo.findByIsAdminTrue();
				}
	//-----------------------------------------------------------------------------------------------------------------------------------------------
				/*@PostMapping("changeAdminStatus/{id}")
				public void changeAdminStatus(@PathVariable int id) {
					
					User u=urepo.findById(id);
					u.setAdmin(false);
					
					urepo.save(u);
					
				}*/
				
				//--------------------------------------------------------------------------------------------------------------
				@PostMapping("saveImage2")
				public ResponseEntity<Object> saveImage2(@RequestParam("file") MultipartFile file, @RequestParam("email") String email) {

					

					// String imageName=new java.util.Date().toString()+".jpg";
					String path = context.getRealPath("/") + "\\images";
					File fl = new File(path);
					if (!fl.exists()) {
						fl.mkdir();
					}
					System.out.println("Image path " + path);
					Path root = Paths.get(path + "\\" + email + ".jpg");

					try {

						if (Files.exists(root)) {
							Files.delete(root); // Delete the existing image file
						}

						Files.copy(file.getInputStream(), root);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.print(e.getMessage());

						message = e.getMessage();
					}

					Map<String, String> data = new HashMap();
					data.put("message", message);
					return new ResponseEntity<>(data, HttpStatus.OK);
				}
				
				
				//--------------------------------------------------------------------------------------------------------------------------------------
				@PostMapping("changeStatusRestore/{email}")
				public ResponseEntity<Object> changeStatusRestore(@PathVariable String email) {

					String message = "";

					User existingUser = urepo.findByEmail(email);

					if (existingUser != null) {

						existingUser.setStatus(0);
						urepo.save(existingUser);
						message="User Approved";
					}
					Map<String, String> data = new HashMap();
					data.put("token", message);
					return new ResponseEntity<>(data, HttpStatus.OK);
				}
				//------------------------------------------------------------------------------------------------------------------------
				@GetMapping("getAge/{id}")
				public String getAge(@PathVariable int id) {
					String message="";
					User u=urepo.findById(id);
					
					if(u.getAge()==0) {
						message="no";
					}else {
						message="yes";
					}
					return message;
				}
				//----------------------------------------------------------------------------------------------------------------------------------
				@PostMapping("deleteUser/{email}")
				public ResponseEntity<Object> deleteUser(@PathVariable String email) {

					String message = "";

					User existingUser = urepo.findByEmail(email);

					if (existingUser != null) {

						existingUser.setStatus(2);
						urepo.save(existingUser);
						message="deleted";
					}
					Map<String, String> data = new HashMap();
					data.put("token", message);
					return new ResponseEntity<>(data, HttpStatus.OK);
				}
}
