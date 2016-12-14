package com.liquidforte.scheduler;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Produces({ MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_JSON })
@Singleton
@Provider
public class JacksonProvider implements ContextResolver<ObjectMapper> {
	private static final ObjectMapper MAPPER = new ObjectMapper();

	static {
		MAPPER.setSerializationInclusion(Include.ALWAYS);
		MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
	}

	public JacksonProvider() {
	}

	@Override
	public ObjectMapper getContext(Class<?> type) {
		return MAPPER;
	}
}
