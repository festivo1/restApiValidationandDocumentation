package com.rest.restfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

//Controller
@RestController
public class HelloWorldController {
	//GET method
	//URI--/hello
	//method-"Hello World"
	
	@Autowired
	private MessageSource messageSource;
@GetMapping(path = "/hello")
	public String helloWorld() {
		return "Hello World";
	}
//hello-world bean 
@GetMapping(path = "/helloBean")
public HelloWorldBean helloWorldBean() {
	return new HelloWorldBean("Hello World");
}
//hello/path-variable/in28minutes
@GetMapping(path = "/hello/path-variable/{name}")
public HelloWorldBean helloWorldPathVar(@PathVariable String name) {
	return new HelloWorldBean("Hello World "+name);
}

@GetMapping(path = "/hello-word-internationalized")
public String  helloWorldInternationalized() {
	return messageSource.
			getMessage("good.morning.message",
					null, LocaleContextHolder.getLocale());
}
}
