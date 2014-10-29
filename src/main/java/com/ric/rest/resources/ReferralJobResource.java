package com.ric.rest.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ric.rest.providers.Secure;
import com.ric.util.AppConstants;

/**
 * @author siva
 *
 */

@Path("/")
public class ReferralJobResource {
	static final Logger log = LoggerFactory.getLogger(ReferralJobResource.class);	

	@POST
	@Secure
	@Path("/postjob")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createJob(@Context SecurityContext sc) {
		if (sc.isUserInRole(AppConstants.ROLE_USER))
			return Response.ok(
					sc.getUserPrincipal().getName() + "has created a job")
					.build();
		return Response.status(Response.Status.UNAUTHORIZED).entity("job not created ").build();

	}

	@DELETE
	@Secure
	@Path("/deletejob")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteJob(@Context SecurityContext sc) {

		if (sc.isSecure())
			return Response.status(200).entity("deletejob executed").build();
		return Response.status(200).entity("deletejob  not executed").build();
	}

}
