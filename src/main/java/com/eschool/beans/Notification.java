package com.eschool.beans;



public class Notification {
	
	
	
	private String receiver;
    private String subject;
    private String body;
    private int template;
    
    
	public Notification(String receiver, String subject, String body, int template) {
		this.receiver = receiver;
		this.subject = subject;
		this.body = body;
		this.template = template;
	}


	public Notification() {

	}


	public String getReceiver() {
		return receiver;
	}


	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getBody() {
		return body;
	}


	public void setBody(String body) {
		this.body = body;
	}


	public int getTemplate() {
		return template;
	}


	public void setTemplate(int template) {
		this.template = template;
	}
    
    
	

}
