package com.eschool.beans;

import java.util.ArrayList;

public class AttendaceDetails {
	private ArrayList<Integer> events;
	private ArrayList<Integer> users;
	private ArrayList<Boolean> isPresent;
	public AttendaceDetails(ArrayList<Integer> events, ArrayList<Integer> users, ArrayList<Boolean> isPresent) {
		this.events = events;
		this.users = users;
		this.isPresent = isPresent;
	}
	public AttendaceDetails() {
		
	}
	
	public ArrayList<Integer> getEvents() {
		return events;
	}
	public void setEvents(ArrayList<Integer> events) {
		this.events = events;
	}
	public ArrayList<Integer> getUsers() {
		return users;
	}
	public void setUsers(ArrayList<Integer> users) {
		this.users = users;
	}
	public ArrayList<Boolean> getIsPresent() {
		return isPresent;
	}
	public void setIsPresent(ArrayList<Boolean> isPresent) {
		this.isPresent = isPresent;
	}
	
    
}
