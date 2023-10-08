package com.eschool.beans;

import java.util.Date;

public class TravelEventUser {
	
	int travelId;
	int eventId;
	int userId;
	String fromCity;
	String fromState;	
	String fromCountry;
	Date arrivalDate;
	String arrivalTime;
	String arrivalModeOfTransport;
	String arrivalTrainNumber;
	String arrivalTrainName;
	Date departureDate;
	String departureTime;
	String departureModeOfTransport;
	String departureTrainName;
	String description;
	String userName;
	String eventName;

	public TravelEventUser() {
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

	public String getFromState() {
		return fromState;
	}

	public void setFromState(String fromState) {
		this.fromState = fromState;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public TravelEventUser(int travelId, int eventId, int userId, String fromCity, String fromState, String fromCountry,
			Date arrivalDate, String arrivalTime, String arrivalModeOfTransport, String arrivalTrainNumber,
			String arrivalTrainName, Date departureDate, String departureTime, String departureModeOfTransport,
			String departureTrainName, String description, String userName, String eventName) {
		super();
		this.travelId = travelId;
		this.eventId = eventId;
		this.userId = userId;
		this.fromCity = fromCity;
		this.fromState = fromState;
		this.fromCountry = fromCountry;
		this.arrivalDate = arrivalDate;
		this.arrivalTime = arrivalTime;
		this.arrivalModeOfTransport = arrivalModeOfTransport;
		this.arrivalTrainNumber = arrivalTrainNumber;
		this.arrivalTrainName = arrivalTrainName;
		this.departureDate = departureDate;
		this.departureTime = departureTime;
		this.departureModeOfTransport = departureModeOfTransport;
		this.departureTrainName = departureTrainName;
		this.description = description;
		this.userName = userName;
		this.eventName = eventName;
	}
		
}
