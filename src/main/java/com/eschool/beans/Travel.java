
package com.eschool.beans;

import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    private Date arrivalTime;

    private String arrivalModeOfTransport;

    private String arrivalTrainNumber;

    private String arrivalTrainName;

    private Date departureDate;

    private Date departureTime;

    private String departureModeOfTransport;

    private String departureTrainNumber;

    private String departureTrainName;


    public Travel() {
    	
    }


	public Travel(int travelId, int eventId, int userId, String fromCity, String fromCountry, Date arrivalDate,
			Date arrivalTime, String arrivalModeOfTransport, String arrivalTrainNumber, String arrivalTrainName,
			Date departureDate, Date departureTime, String departureModeOfTransport, String departureTrainNumber,
			String departureTrainName) {
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


	public Date getArrivalTime() {
		return arrivalTime;
	}


	public void setArrivalTime(Date arrivalTime) {
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


	public Date getDepartureTime() {
		return departureTime;
	}


	public void setDepartureTime(Date departureTime) {
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
    
    

   

}
