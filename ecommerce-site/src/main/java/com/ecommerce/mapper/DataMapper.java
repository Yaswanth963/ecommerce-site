package com.ecommerce.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

/**
 * 
 * @author yaswanth.perumalla
 * 
 *         A mapper class that maps data from one format to other
 *
 */
@Component
public class DataMapper {

	@Autowired
	ObjectMapper oMap;

	@Autowired
	Gson gson;

	/**
	 * 
	 * Accepts data of Object form and converts into json
	 * 
	 * @return
	 */
	public <T> String toJson(Object t) {
		return gson.toJson(t);
	}

	/**
	 * 
	 * Accepts json data and converts into desired Class form
	 * 
	 * @return
	 */
	public <T> T fromJson(Class<T> t, String json) {
		return gson.fromJson(json, t);
	}
}
