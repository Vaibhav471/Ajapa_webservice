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
	String fullName;	
	String dob;	
	String mobileNum;	
	String password;	
	String gender, bloodGrp, occupation, qualification, addressLinep,addressLines, country, state, city, dikshaDt;
    int age;
    String whatsappNum;
    double pincode;
    int status;    
    String userType="member";    
    int familyId;    
    String identifier;    
    boolean isAdmin;
	public User(int id, @Email(message = "Enter a valid Email address") String email, String fullName, String dob,
			String mobileNum, String password, String gender, String bloodGrp, String occupation, String qualification,
			String addressLinep, String addressLines, String country, String state, String city, String dikshaDt,
			int age, String whatsappNum, double pincode, int status, String userType, int familyId, String identifier,
			boolean isAdmin) {
		this.id = id;
		this.email = email;
		this.fullName = fullName;
		this.dob = dob;
		this.mobileNum = mobileNum;
		this.password = password;
		this.gender = gender;
		this.bloodGrp = bloodGrp;
		this.occupation = occupation;
		this.qualification = qualification;
		this.addressLinep = addressLinep;
		this.addressLines = addressLines;
		this.country = country;
		this.state = state;
		this.city = city;
		this.dikshaDt = dikshaDt;
		this.age = age;
		this.whatsappNum = whatsappNum;
		this.pincode = pincode;
		this.status = status;
		this.userType = userType;
		this.familyId = familyId;
		this.identifier = identifier;
		this.isAdmin = isAdmin;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNum() {
		return mobileNum;
	}

	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
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

	public String getWhatsappNum() {
		return whatsappNum;
	}

	public void setWhatsappNum(String whatsappNum) {
		this.whatsappNum = whatsappNum;
	}

	public String getBloodGrp() {
		return bloodGrp;
	}

	public void setBloodGrp(String bloodGrp) {
		this.bloodGrp = bloodGrp;
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

	public String getAddressLinep() {
		return addressLinep;
	}

	public void setAddressLinep(String addressLinep) {
		this.addressLinep = addressLinep;
	}

	public String getAddressLines() {
		return addressLines;
	}

	public void setAddressLines(String addressLines) {
		this.addressLines = addressLines;
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

	public String getDikshaDt() {
		return dikshaDt;
	}

	public void setDikshaDt(String dikshaDt) {
		this.dikshaDt = dikshaDt;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User() {

	}
	
	

	
	
}
