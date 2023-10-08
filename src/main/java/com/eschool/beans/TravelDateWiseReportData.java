package com.eschool.beans;
public class TravelDateWiseReportData {
String travelDate;
int totalPersons,totalFamilies,totalSeniorCitizens,totalKids,totalMaleMembers,totalFemaleMembers;
public String getTravelDate() {
	return travelDate;
}
public void setTravelDate(String travelDate) {
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
public TravelDateWiseReportData(String travelDate, int totalPersons, int totalFamilies, int totalSeniorCitizens,
		int totalKids, int totalMaleMembers, int totalFemaleMembers) {
	
	this.travelDate = travelDate;
	this.totalPersons = totalPersons;
	this.totalFamilies = totalFamilies;
	this.totalSeniorCitizens = totalSeniorCitizens;
	this.totalKids = totalKids;
	this.totalMaleMembers = totalMaleMembers;
	this.totalFemaleMembers = totalFemaleMembers;
}


}
