package com.ric.rest.providers;

import java.security.Principal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ric.domain.Session;
import com.ric.domain.User;

public class RICSecurityContext implements SecurityContext {

	static final Logger log = LoggerFactory.getLogger(RICSecurityContext.class);

	private User user;

	private Session session;

	public RICSecurityContext(Session session, User user) {
		this.session = session;
		this.user = user;
	}

	@Override
	public String getAuthenticationScheme() {

		// Returns one of the static members BASIC_AUTH, FORM_AUTH,
		// CLIENT_CERT_AUTH, DIGEST_AUTH (suitable for == comparison) or the
		// container-specific string indicating the authentication scheme, or
		// null if the request was not authenticated.

		return SecurityContext.BASIC_AUTH;
	}

	@Override
	public Principal getUserPrincipal() {

		// Returns a java.security.Principal object containing the name of the
		// current authenticated user. If the user has not been authenticated,
		// the method returns null.
		return user;
	}

	@Override
	public boolean isSecure() {
		// Returns a boolean indicating whether this request was made using a
		// secure channel, such as HTTPS.
		boolean isSecure = false;
		if(null != session){
			isSecure = true;
		}else {
			Response unAuthorized = Response.status(Response.Status.UNAUTHORIZED)
					.entity("Not a Authorized user").build();
			throw new WebApplicationException(unAuthorized);
		}
		return isSecure;
	}

	@Override
	public boolean isUserInRole(String role) {

		// Returns a boolean indicating whether the authenticated user is
		// included in the specified logical "role"
        boolean isUserInRole = false;
        
        //check weather user is valid or not
		if (isSecure()) {
			
			 //check user has the specified role
			 if(user.getRoles().contains(role)) {
				 isUserInRole = true;
			 } else {
				// Forbidden (valid user but don't have the authorized role)
					Response denied = Response.status(Response.Status.FORBIDDEN)
							.entity("Permission Denied").build();
					throw new WebApplicationException(denied);
			 }
			
			
			
		} else {
			Response unAuthorized = Response.status(Response.Status.UNAUTHORIZED)
					.entity("Not a Authorized user").build();
			throw new WebApplicationException(unAuthorized);
		}


		return isUserInRole;
	}
}