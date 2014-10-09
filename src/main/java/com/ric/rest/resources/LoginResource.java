package com.ric.rest.resources;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;

import com.ric.domain.User;
import com.ric.services.LoginService;
import com.ric.util.AppConstants;

@Path("/")
public class LoginResource {

	@Autowired
	private LoginService loginService;

	/**
	 * @return the loginService
	 */
	public LoginService getLoginService() {
		return loginService;
	}

	/**
	 * @param loginService
	 *            the loginService to set
	 */
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	/**
	 * Method handling HTTP POST requests to signup.
	 *
	 * @return Respone with status code and sessionId/message
	 */
	@POST()
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/signup")
	public Response signup(User user) {
		
		Set<String> roles = new HashSet<String>();
		roles.add(AppConstants.ROLE_USER);
		user.setRoles(roles);

		return loginService.save(user);
	}

	/**
	 * Method handling HTTP GET requests to login.
	 *
	 * @return Response with status code and sessionId/message
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/login")
	public Response login(@QueryParam("username") String userName,
			@QueryParam("password") String password) {

		return loginService.authenticate(userName, password);
	}

	/**
	 * Method handling HTTP DELETE requests to logout.
	 *
	 * @return plain text
	 */
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/logout")
	public String logout(@HeaderParam("secretKey") String key) {

		loginService.deleteSession(key);
		return "bye; see you soon";
	}

	/**
	 * Method handling HTTP GET requests to forgetPassword.
	 *
	 * @return Response with status code and sessionId/message
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/forgetPassword/{mailId}")
	public Response forgetPassword(@PathParam("mailId") String mailId) {

		return loginService.forgetPassword(mailId);
	}

	/**
	 * Method handling HTTP PUT requests to forgetPassword.
	 *
	 * @return Response with status code and sessionId/message
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/forgetPassword/{sessionid}")
	public Response resetPassword(@PathParam("sessionid") String sessionId,
			@FormParam("password") String password) {

		return loginService.resetPassword(sessionId, password);
	}

}
