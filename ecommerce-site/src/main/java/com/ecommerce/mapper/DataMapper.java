package com.ecommerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@Component
public class DataMapper {

	@Autowired
	ObjectMapper oMap;

	@Autowired
	Gson gson;

	public <T> String toJson(Object t) {
		return gson.toJson(t);
	}

	public <T> T fromJson(Class<T> t, String json) {
		return gson.fromJson(json, t);
	}
}
