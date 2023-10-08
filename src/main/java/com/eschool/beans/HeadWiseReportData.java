package com.eschool.beans;

import java.sql.Date;
import java.util.ArrayList;

public class HeadWiseReportData {
	String headName;
	String emailId;
	ArrayList<String> memberNames=new ArrayList<>();
	ArrayList<String> reachingCity=new ArrayList<>();
	ArrayList<Date> reachingDate=new ArrayList<>();
	ArrayList<String> reachingMode=new ArrayList<>();
	ArrayList<String> reachingTrainDetails=new ArrayList<>();
	ArrayList<Date> leavingDate=new ArrayList<>();
	ArrayList<String> leavingMode=new ArrayList<>();
	ArrayList<String> leavingTrainDetails=new ArrayList<>();	
	int totalPersons,totalSeniorCitizens,totalKids,totalMaleMembers,totalFemaleMembers;
	public HeadWiseReportData(String headName, String emailId, ArrayList<String> memberNames,
			ArrayList<String> reachingCity, ArrayList<Date> reachingDate, ArrayList<String> reachingMode,
			ArrayList<String> reachingTrainDetails, ArrayList<Date> leavingDate, ArrayList<String> leavingMode,
			ArrayList<String> leavingTrainDetails, int totalPersons, int totalSeniorCitizens, int totalKids,
			int totalMaleMembers, int totalFemaleMembers) {
		this.headName = headName;
		this.emailId = emailId;
		this.memberNames = memberNames;
		this.reachingCity = reachingCity;
		this.reachingDate = reachingDate;
		this.reachingMode = reachingMode;
		this.reachingTrainDetails = reachingTrainDetails;
		this.leavingDate = leavingDate;
		this.leavingMode = leavingMode;
		this.leavingTrainDetails = leavingTrainDetails;
		this.totalPersons = totalPersons;
		this.totalSeniorCitizens = totalSeniorCitizens;
		this.totalKids = totalKids;
		this.totalMaleMembers = totalMaleMembers;
		this.totalFemaleMembers = totalFemaleMembers;
	}
	public String getHeadName() {
		return headName;
	}
	public void setHeadName(String headName) {
		this.headName = headName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public ArrayList<String> getMemberNames() {
		return memberNames;
	}
	public void setMemberNames(ArrayList<String> memberNames) {
		this.memberNames = memberNames;
	}
	public ArrayList<String> getReachingCity() {
		return reachingCity;
	}
	public void setReachingCity(ArrayList<String> reachingCity) {
		this.reachingCity = reachingCity;
	}
	public ArrayList<Date> getReachingDate() {
		return reachingDate;
	}
	public void setReachingDate(ArrayList<Date> reachingDate) {
		this.reachingDate = reachingDate;
	}
	public ArrayList<String> getReachingMode() {
		return reachingMode;
	}
	public void setReachingMode(ArrayList<String> reachingMode) {
		this.reachingMode = reachingMode;
	}
	public ArrayList<String> getReachingTrainDetails() {
		return reachingTrainDetails;
	}
	public void setReachingTrainDetails(ArrayList<String> reachingTrainDetails) {
		this.reachingTrainDetails = reachingTrainDetails;
	}
	public ArrayList<Date> getLeavingDate() {
		return leavingDate;
	}
	public void setLeavingDate(ArrayList<Date> leavingDate) {
		this.leavingDate = leavingDate;
	}
	public ArrayList<String> getLeavingMode() {
		return leavingMode;
	}
	public void setLeavingMode(ArrayList<String> leavingMode) {
		this.leavingMode = leavingMode;
	}
	public ArrayList<String> getLeavingTrainDetails() {
		return leavingTrainDetails;
	}
	public void setLeavingTrainDetails(ArrayList<String> leavingTrainDetails) {
		this.leavingTrainDetails = leavingTrainDetails;
	}
	public int getTotalPersons() {
		return totalPersons;
	}
	public void setTotalPersons(int totalPersons) {
		this.totalPersons = totalPersons;
	}
	public int getTotalSeniorCitizens() {
		return totalSeniorCitizens;
	}
	public void setTotalSeniorCitizens(int totalSeniorCitizens) {
		this.totalSeniorCitizens = totalSeniorCitizens;
	}
	public int getTotalKids() {
		return totalKids;
	}
	public void setTotalKids(int totalKids) {
		this.totalKids = totalKids;
	}
	public int getTotalMaleMembers() {
		return totalMaleMembers;
	}
	public void setTotalMaleMembers(int totalMaleMembers) {
		this.totalMaleMembers = totalMaleMembers;
	}
	public int getTotalFemaleMembers() {
		return totalFemaleMembers;
	}
	public void setTotalFemaleMembers(int totalFemaleMembers) {
		this.totalFemaleMembers = totalFemaleMembers;
	}
	public HeadWiseReportData() {
		
	}
	
}
