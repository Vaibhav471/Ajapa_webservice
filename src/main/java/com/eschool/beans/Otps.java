package com.eschool.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="otps")
public class Otps {
	@Id
	int id;
	int otp;
	String pno;
	
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
	public String getPno() {
		return pno;
	}
	public void setPno(String pno) {
		this.pno = pno;
	}
	public Otps(int id, int otp, String pno) {
		this.id = id;
		this.otp = otp;
		this.pno = pno;
	}
	public Otps() {
	}
	
	
	
	

}
