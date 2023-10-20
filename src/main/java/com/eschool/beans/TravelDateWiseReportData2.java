package com.eschool.beans;

import java.sql.Date;
import java.util.ArrayList;

public class TravelDateWiseReportData2 {
	Date travelDate;
	int totalPersons,totalFamilies;
    String trainNames[]=new String[4];
    int trainPerson[]=new int[4];
    int flightPerson[]=new int[4];
    int roadPerson[]=new int[4];
		public TravelDateWiseReportData2(Date travelDate, int totalPersons, int totalFamilies, String[] trainNames,
			int[] trainPerson, int[] flightPerson, int[] roadPerson) {
		
		this.travelDate = travelDate;
		this.totalPersons = totalPersons;
		this.totalFamilies = totalFamilies;
		this.trainNames = trainNames;
		this.trainPerson = trainPerson;
		this.flightPerson = flightPerson;
		this.roadPerson = roadPerson;
	}
		public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public int getTotalPersons() {
		return totalPersons;
	}
	public void setTotalPersons(int totalPersons) {
		this.totalPersons = totalPersons;
	}
	public int getTotalFamilies() {
		return totalFamilies;
	}
	public void setTotalFamilies(int totalFamilies) {
		this.totalFamilies = totalFamilies;
	}
	public String[] getTrainNames() {
		return trainNames;
	}
	public void setTrainNames(String[] trainNames) {
		this.trainNames = trainNames;
	}
	public int[] getTrainPerson() {
		return trainPerson;
	}
	public void setTrainPerson(int[] trainPerson) {
		this.trainPerson = trainPerson;
	}
	public int[] getFlightPerson() {
		return flightPerson;
	}
	public void setFlightPerson(int[] flightPerson) {
		this.flightPerson = flightPerson;
	}
	public int[] getRoadPerson() {
		return roadPerson;
	}
	public void setRoadPerson(int[] roadPerson) {
		this.roadPerson = roadPerson;
	}
		public TravelDateWiseReportData2() {
	}  
    
}
