package com.ric.services;

import javax.ws.rs.core.Response;

import com.ric.domain.User;

public interface LoginService {

	public Response save(User user);

	public Response authenticate(String userName, String password);

	public void deleteSession(String key);
	
	public Response forgetPassword(String mailId);
	
	public Response resetPassword(String sessionId, String password);

}
