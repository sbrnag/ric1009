package com.ric.services;

public interface MailService {
	
	public void sendMail(String from, String to, String subject, String body);

}
