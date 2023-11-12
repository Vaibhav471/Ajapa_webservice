package com.eschool.beans;

public class UserForAttendance {
User user;
boolean isPresent;
public UserForAttendance() {
}
public UserForAttendance(User user, boolean isPresent) {
	
	this.user = user;
	this.isPresent = isPresent;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
public boolean isPresent() {
	return isPresent;
}
public void setPresent(boolean isPresent) {
	this.isPresent = isPresent;
}

}
