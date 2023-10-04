
package com.eschool.beans;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "travel")
public class Travel {

    @Id
    private int travelId;

    private int eventId;
    
    private int userId;

    private String fromCity;

    private String fromCountry;

    private Date arrivalDate;

    private String arrivalTime;

    private String arrivalModeOfTransport;

    private String arrivalTrainNumber;

    private String arrivalTrainName;

    private Date departureDate;

    private String departureTime;

    private String departureModeOfTransport;

    private String departureTrainNumber;

    private String departureTrainName;
    
    private String description;
    
    private Date lockArrivalDate;
    
    private Date lockDepartureDate;
    
    private int familyId;
    
    
    

    

	
	public Travel() {

	}







	public Travel(int travelId, int eventId, int userId, String fromCity, String fromCountry, Date arrivalDate,
			String arrivalTime, String arrivalModeOfTransport, String arrivalTrainNumber, String arrivalTrainName,
			Date departureDate, String departureTime, String departureModeOfTransport, String departureTrainNumber,
			String departureTrainName, String description, Date lockArrivalDate, Date lockDepartureDate, int familyId) {
		this.travelId = travelId;
		this.eventId = eventId;
		this.userId = userId;
		this.fromCity = fromCity;
		this.fromCountry = fromCountry;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.arrivalModeOfTransport = arrivalModeOfTransport;
		this.arrivalTrainNumber = arrivalTrainNumber;
		this.arrivalTrainName = arrivalTrainName;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.departureModeOfTransport = departureModeOfTransport;
		this.departureTrainNumber = departureTrainNumber;
		this.departureTrainName = departureTrainName;
		this.description = description;
		this.lockArrivalDate = lockArrivalDate;
		this.lockDepartureDate = lockDepartureDate;
		this.familyId = familyId;
	}







	public int getTravelId() {
		return travelId;
	}







	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}







	public int getEventId() {
		return eventId;
	}







	public void setEventId(int eventId) {
		this.eventId = eventId;
	}







	public int getUserId() {
		return userId;
	}







	public void setUserId(int userId) {
		this.userId = userId;
	}







	public String getFromCity() {
		return fromCity;
	}







	public void setFromCity(String fromCity) {
		this.fromCity = fromCity;
	}







	public String getFromCountry() {
		return fromCountry;
	}







	public void setFromCountry(String fromCountry) {
		this.fromCountry = fromCountry;
	}







	public Date getArrivalDate() {
		return arrivalDate;
	}







	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}







	public String getArrivalTime() {
		return arrivalTime;
	}







	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}







	public String getArrivalModeOfTransport() {
		return arrivalModeOfTransport;
	}







	public void setArrivalModeOfTransport(String arrivalModeOfTransport) {
		this.arrivalModeOfTransport = arrivalModeOfTransport;
	}







	public String getArrivalTrainNumber() {
		return arrivalTrainNumber;
	}







	public void setArrivalTrainNumber(String arrivalTrainNumber) {
		this.arrivalTrainNumber = arrivalTrainNumber;
	}







	public String getArrivalTrainName() {
		return arrivalTrainName;
	}







	public void setArrivalTrainName(String arrivalTrainName) {
		this.arrivalTrainName = arrivalTrainName;
	}







	public Date getDepartureDate() {
		return departureDate;
	}







	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}







	public String getDepartureTime() {
		return departureTime;
	}







	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}







	public String getDepartureModeOfTransport() {
		return departureModeOfTransport;
	}







	public void setDepartureModeOfTransport(String departureModeOfTransport) {
		this.departureModeOfTransport = departureModeOfTransport;
	}







	public String getDepartureTrainNumber() {
		return departureTrainNumber;
	}







	public void setDepartureTrainNumber(String departureTrainNumber) {
		this.departureTrainNumber = departureTrainNumber;
	}







	public String getDepartureTrainName() {
		return departureTrainName;
	}







	public void setDepartureTrainName(String departureTrainName) {
		this.departureTrainName = departureTrainName;
	}







	public String getDescription() {
		return description;
	}







	public void setDescription(String description) {
		this.description = description;
	}







	public Date getLockArrivalDate() {
		return lockArrivalDate;
	}







	public void setLockArrivalDate(Date lockArrivalDate) {
		this.lockArrivalDate = lockArrivalDate;
	}







	public Date getLockDepartureDate() {
		return lockDepartureDate;
	}







	public void setLockDepartureDate(Date lockDepartureDate) {
		this.lockDepartureDate = lockDepartureDate;
	}







	public int getFamilyId() {
		return familyId;
	}







	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}






}
