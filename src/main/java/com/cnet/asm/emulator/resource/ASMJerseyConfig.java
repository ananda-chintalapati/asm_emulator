package com.cnet.asm.emulator.resource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cnet.asm.emulator.client.ServiceNowClient;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@ApplicationPath("/asm")
public class ASMJerseyConfig extends ResourceConfig {

	@Autowired
	public ASMJerseyConfig(ObjectMapper objectMapper) {
		// register endpoints
		packages("com.cnet.asm.emulator");
		register(AssetManagementResource.class);
		register(ServiceNowClient.class);
		// register jackson for json
		register(new ObjectMapperContextResolver(objectMapper));
	}

	@Provider
	public static class ObjectMapperContextResolver implements 
                  ContextResolver<ObjectMapper> {
		private final ObjectMapper mapper;
		public ObjectMapperContextResolver(ObjectMapper mapper) {
			this.mapper = mapper;
		}

		@Override
		public ObjectMapper getContext(Class<?> type) {
			return mapper;
		}
	}
}