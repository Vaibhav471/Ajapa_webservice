package com.eschool;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eschool.beans.Admin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class AdminController {
	
	@Autowired
	AdminRepository arepo;
	
	
	@PostMapping("adminSignup")
	public String adminSignup(@RequestBody Admin admin) {
		String message;
		
		try {
		arepo.save(admin);
		message="Admin svaed";
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		return message;
	}
	
	@PostMapping("adminLogin")
	public ResponseEntity<Object> adminLogin(@RequestBody Admin admin) {
		String message;
		String type="";
		Map<String, String> data = new HashMap<>();

		Admin a=new Admin();
		
		try {
			a=arepo.findByIdentifierAndPassword(admin.getIdentifier(),admin.getPassword());
			if(a==null) {
				message="false";
			}
			else {
				String token = Jwts.builder().claim("Identifier",a.getIdentifier()).claim("password", a.getPassword())
						.setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 day
						.signWith(SignatureAlgorithm.HS256,"9wJYK7g67fTRC29iP6VnF89h5sW1rDcT3uXvA0qLmB4zE1pN8rS7zT0qF2eR5vJ3")
						.compact();
				type="admin";
				message=token;
		}
		}
		catch(Exception e) {
			message=e.getMessage();
		}
		
		
		
		data.put("token", message);
		data.put("type", type);
		
		return new ResponseEntity<>(data, HttpStatus.OK);

		

}
	//-------------------------------------------------------------
	@GetMapping("getAllAdminsNew")
	public List<Admin> getAllAdmin(){
		
		return arepo.findAll();
	}
	
}
