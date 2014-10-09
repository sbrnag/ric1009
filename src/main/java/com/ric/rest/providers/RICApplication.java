package com.ric.rest.providers;

import org.glassfish.jersey.server.ResourceConfig;

import com.ric.rest.resources.IsAliveResource;
import com.ric.rest.resources.LoginResource;
import com.ric.rest.resources.ReferralJobResource;

public class RICApplication extends ResourceConfig {

	public RICApplication() {

		register(ResourceFilterBindingFeature.class);
		register(IsAliveResource.class);
		register(LoginResource.class);
		register(ReferralJobResource.class);
	}
}