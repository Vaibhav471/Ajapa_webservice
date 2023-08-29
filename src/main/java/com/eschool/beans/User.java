package com.eschool.beans;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="user")
public class User {
	
	@Id
	int id;
	
	@Email(message="Enter a valid Email address")
	String email;
	
	String full_name;
	
	String dob;
	
	String mobile_num;
	
	
	String password;
	
	String gender, whatsapp_num, blood_grp, occupation, qualification, address_linep,address_lines, country, state, city, diksha_dt;
    int age;
    double pincode;
    int status;
    
    
    
	public User(int id, @Email(message = "Enter a valid Email address") String email, String full_name,
			String dob, String mobile_num, String password, String gender, String whatsapp_num,
			String blood_grp, String occupation, String qualification, String address_linep, String address_lines,
			String country, String state, String city, String diksha_dt, int age, double pincode, int status) {
		this.id = id;
		this.email = email;
		this.full_name = full_name;
		this.dob = dob;
		this.mobile_num = mobile_num;
		this.password = password;
		this.gender = gender;
		this.whatsapp_num = whatsapp_num;
		this.blood_grp = blood_grp;
		this.occupation = occupation;
		this.qualification = qualification;
		this.address_linep = address_linep;
		this.address_lines = address_lines;
		this.country = country;
		this.state = state;
		this.city = city;
		this.diksha_dt = diksha_dt;
		this.age = age;
		this.pincode = pincode;
		this.status = status;
	}



	public User() {

	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getFull_name() {
		return full_name;
	}



	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}



	public String getDob() {
		return dob;
	}



	public void setDob(String dob) {
		this.dob = dob;
	}



	public String getMobile_num() {
		return mobile_num;
	}



	public void setMobile_num(String mobile_num) {
		this.mobile_num = mobile_num;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public String getWhatsapp_num() {
		return whatsapp_num;
	}



	public void setWhatsapp_num(String whatsapp_num) {
		this.whatsapp_num = whatsapp_num;
	}



	public String getBlood_grp() {
		return blood_grp;
	}



	public void setBlood_grp(String blood_grp) {
		this.blood_grp = blood_grp;
	}



	public String getOccupation() {
		return occupation;
	}



	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}



	public String getQualification() {
		return qualification;
	}



	public void setQualification(String qualification) {
		this.qualification = qualification;
	}



	public String getAddress_linep() {
		return address_linep;
	}



	public void setAddress_linep(String address_linep) {
		this.address_linep = address_linep;
	}



	public String getAddress_lines() {
		return address_lines;
	}



	public void setAddress_lines(String address_lines) {
		this.address_lines = address_lines;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getDiksha_dt() {
		return diksha_dt;
	}



	public void setDiksha_dt(String diksha_dt) {
		this.diksha_dt = diksha_dt;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public double getPincode() {
		return pincode;
	}



	public void setPincode(double pincode) {
		this.pincode = pincode;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}
    
    

	
}
