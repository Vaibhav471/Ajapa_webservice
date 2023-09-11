package com.eschool;

import java.io.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
	@PostMapping("signup")
	public ResponseEntity<Object> saveUser(@RequestBody User user) {
		message="";
		Map<String, String> data = new HashMap<>();
		try {
			urepo.save(user);
			message = "Data saved successfully";			
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
		Map<String, String> data = new HashMap<>();
		try
		{
		User u = urepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		if (u != null) {
			// The code to convert user information into JWT token
			String token = Jwts.builder().claim("full_name", u.getFull_name()).claim("email", u.getEmail())
					.claim("mobile_number", u.getMobile_num()).claim("id", u.getId()).claim("type", u.getUser_type())
					.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
					.signWith(SignatureAlgorithm.HS256,"9wJYK7g67fTRC29iP6VnF89h5sW1rDcT3uXvA0qLmB4zE1pN8rS7zT0qF2eR5vJ3")
					.compact();
			type=u.getUser_type();
			
			token_message = token;
		} else {
			token_message = "Invalid User information (0)";
		}
		}
		catch(Exception e)
		{
			token_message=e.getMessage();
		}
		data.put("token", token_message);
		data.put("type", type);
		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	// ---------------------------------UPDATE USER----------------------------------------------------------------------------------------------------------------------
	@PutMapping("updateUser")
	public ResponseEntity<Object> updateUser(@RequestBody User user,@RequestHeader("Authorization") String authorizationHeader) {
		String message = "";
		String email = "";
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

			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Failed to decode JWT: " + e.getMessage());
			}
		}
		User existingUser = urepo.findByEmail(email);
		if (existingUser != null) {
			if (user.getFull_name() != null) {
				existingUser.setFull_name(user.getFull_name()); // this is a mandatory field, if client sends it null at the time of updation, we do not set null as fiel value.
			}
			if (user.getDob() != null) {
				existingUser.setDob(user.getDob());
			}
			if (user.getMobile_num() != null) {
				existingUser.setMobile_num(user.getMobile_num());
			}
			if (user.getGender() != null) {
				existingUser.setGender(user.getGender());
			}
			existingUser.setWhatsapp_num(user.getWhatsapp_num());
			if (user.getPassword() != null) {
				existingUser.setPassword(user.getPassword());
			}
			existingUser.setBlood_grp(user.getBlood_grp());
			existingUser.setOccupation(user.getOccupation());
			existingUser.setQualification(user.getQualification());
			existingUser.setAddress_linep(user.getAddress_linep());
			existingUser.setAddress_lines(user.getAddress_lines());
			if (user.getCountry() != null) {
				existingUser.setCountry(user.getCountry());
			}
			existingUser.setState(user.getState());
			if (user.getCity() != null) {
				existingUser.setCity(user.getCity());
			}

			existingUser.setDiksha_dt(user.getDiksha_dt());
			existingUser.setAge(user.getAge());
			existingUser.setPincode(user.getPincode());

			urepo.save(existingUser);

			message = "user updated";
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
		
		@GetMapping("getUsersToApprove")
		public List<User> getUsersToApprove(){
			
			return urepo.findUsersByStatus(0);
		}
		
		
	
}
