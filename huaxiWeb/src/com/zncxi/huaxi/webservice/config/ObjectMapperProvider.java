package com.zncxi.huaxi.webservice.config;

import java.text.SimpleDateFormat;

import javax.ws.rs.ext.ContextResolver;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig.Feature;

public class ObjectMapperProvider implements ContextResolver<ObjectMapper> {

	public ObjectMapper getContext(Class<?> type) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objectMapper.configure(Feature.WRITE_DATES_AS_TIMESTAMPS, false);
		return objectMapper;
	}

}
