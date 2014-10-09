package com.ric.rest.providers;

import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;
 
@Provider
public class ResourceFilterBindingFeature implements DynamicFeature {
 
  @Override
  public void configure(ResourceInfo resourceInfo, FeatureContext context) {
    if (resourceInfo.getResourceMethod().isAnnotationPresent(Secure.class)) {
      context.register(RICSecurityContextFilter.class);
    }
    
  }
}
