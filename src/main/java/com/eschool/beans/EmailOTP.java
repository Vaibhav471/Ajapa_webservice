package com.eschool.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="email_otp")
public class EmailOTP {
	
	@Id
	int id;
	int otp;
	String email;
	
	public EmailOTP(int id, int otp, String email) {
		this.id = id;
		this.otp = otp;
		this.email = email;
	}

	public EmailOTP() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOtp() {
		return otp;
	}

	public void setOtp(int otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	

}
