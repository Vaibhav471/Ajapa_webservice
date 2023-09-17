
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
    private int travel_id;

   // @ManyToOne
    //@JoinTable(name="event",joinColumns={@JoinColumn(name="event_id")})
    private int eventId;
    
    

   //@ManyToOne
   //@JoinTable(name="user",joinColumns={@JoinColumn(name="id")})
    private int userId;

    private String from_city;

    private String from_country;

    private Date arrival_date;

    private String arrival_time;

    private String arrival_mode_of_transport;

    private String arrival_train_number;

    private String arrival_train_name;

    private Date departure_date;

    private String departure_time;

    private String departure_mode_of_transport;

    private String departure_train_number;

    private String departure_train_name;
    
    private String description;
    
    
    

    

	
	public Travel() {

	}

	public int getTravel_id() {
		return travel_id;
	}

	public void setTravel_id(int travel_id) {
		this.travel_id = travel_id;
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

	

	public String getFrom_city() {
		return from_city;
	}

	public void setFrom_city(String from_city) {
		this.from_city = from_city;
	}

	public String getFrom_country() {
		return from_country;
	}

	public void setFrom_country(String from_country) {
		this.from_country = from_country;
	}

	public Date getArrival_date() {
		return arrival_date;
	}

	public void setArrival_date(Date arrival_date) {
		this.arrival_date = arrival_date;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_time) {
		this.arrival_time = arrival_time;
	}

	public String getArrival_mode_of_transport() {
		return arrival_mode_of_transport;
	}

	public void setArrival_mode_of_transport(String arrival_mode_of_transport) {
		this.arrival_mode_of_transport = arrival_mode_of_transport;
	}

	public String getArrival_train_number() {
		return arrival_train_number;
	}

	public void setArrival_train_number(String arrival_train_number) {
		this.arrival_train_number = arrival_train_number;
	}

	public String getArrival_train_name() {
		return arrival_train_name;
	}

	public void setArrival_train_name(String arrival_train_name) {
		this.arrival_train_name = arrival_train_name;
	}

	public Date getDeparture_date() {
		return departure_date;
	}

	public void setDeparture_date(Date departure_date) {
		this.departure_date = departure_date;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public String getDeparture_mode_of_transport() {
		return departure_mode_of_transport;
	}

	public void setDeparture_mode_of_transport(String departure_mode_of_transport) {
		this.departure_mode_of_transport = departure_mode_of_transport;
	}

	public String getDeparture_train_number() {
		return departure_train_number;
	}

	public void setDeparture_train_number(String departure_train_number) {
		this.departure_train_number = departure_train_number;
	}

	public String getDeparture_train_name() {
		return departure_train_name;
	}

	public void setDeparture_train_name(String departure_train_name) {
		this.departure_train_name = departure_train_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public Travel(int travel_id, int eventId, int userId, String from_city, String from_country, Date arrival_date,
			String arrival_time, String arrival_mode_of_transport, String arrival_train_number,
			String arrival_train_name, Date departure_date, String departure_time, String departure_mode_of_transport,
			String departure_train_number, String departure_train_name, String description) {
		this.travel_id = travel_id;
		this.eventId = eventId;
		this.userId = userId;
		this.from_city = from_city;
		this.from_country = from_country;
		this.arrival_date = arrival_date;
		this.arrival_time = arrival_time;
		this.arrival_mode_of_transport = arrival_mode_of_transport;
		this.arrival_train_number = arrival_train_number;
		this.arrival_train_name = arrival_train_name;
		this.departure_date = departure_date;
		this.departure_time = departure_time;
		this.departure_mode_of_transport = departure_mode_of_transport;
		this.departure_train_number = departure_train_number;
		this.departure_train_name = departure_train_name;
		this.description = description;
	}

	
	
	

	

	

    
	

}
