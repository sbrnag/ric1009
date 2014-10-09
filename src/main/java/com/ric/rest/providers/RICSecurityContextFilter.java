package com.ric.rest.providers;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;

import com.ric.domain.Session;
import com.ric.domain.User;
import com.ric.mongodb.repository.SessionRepository;
import com.ric.mongodb.repository.UserRepository;
import com.ric.util.AppConstants;

public class RICSecurityContextFilter implements ContainerRequestFilter {

	@Autowired
	private SessionRepository sessionRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void filter(ContainerRequestContext requestcontext)
			throws IOException {

		final String sessionId = requestcontext
				.getHeaderString(AppConstants.SECRET_KEY);
		
		User user = null;
		Session session = null;

		if (sessionId != null && sessionId.length() > 0) {
			session = sessionRepository.getSessionByID(sessionId);
			if (null != session) {
				user = userRepository.findByUserName(session.getUsername());
			}
		}
		requestcontext
				.setSecurityContext(new RICSecurityContext(session, user));
	}

}