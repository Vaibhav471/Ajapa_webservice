package com.eschool.beans;

import java.io.InputStream;

public class UserImage {
	
	int id;
	String email;
	InputStream image;
	String reteieve_image;
	
	public UserImage(int id, String email, InputStream image, String reteieve_image) {
		this.id = id;
		this.email = email;
		this.image = image;
		this.reteieve_image = reteieve_image;
	}

	public UserImage() {

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

	public InputStream getImage() {
		return image;
	}

	public void setImage(InputStream image) {
		this.image = image;
	}

	public String getReteieve_image() {
		return reteieve_image;
	}

	public void setReteieve_image(String reteieve_image) {
		this.reteieve_image = reteieve_image;
	}
	
	

}
