package com.rest.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

	//send field1 and field2 in response 
	@GetMapping(path = "/filtering")
	public MappingJacksonValue retrieveSomeBean() {
		SomeBean someBean= new SomeBean("value1", "value2", "value3");
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(someBean);
		mapping.setFilters(filters);
		
		return mapping;
		
		
	}
	//field2, field3
	@GetMapping(path = "/filtering-list")
	public MappingJacksonValue retrieveListOfSomeBean() {
		List<SomeBean> list=Arrays.asList(new SomeBean("value1", "value2", "value3"),
				new SomeBean("value21", "value22", "value23"));
		
		SimpleBeanPropertyFilter filter=SimpleBeanPropertyFilter
				.filterOutAllExcept("field3","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		MappingJacksonValue mapping = new MappingJacksonValue(list);
		mapping.setFilters(filters);
		return mapping;
	}

}
