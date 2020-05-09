package com.sample.boot.controller;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.boot.dto.Greeting;

@RestController()
@RequestMapping("/greeting")
public class GreetingController {

	@Value("${test.property}")
	private String applicationValue;
	
	@Value("${array.numbers}")
	private List<Integer> arrayNumber;

	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();

	// boot/greeting
	@RequestMapping(value="/", method=RequestMethod.GET)
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	/**
	 * test get configuration value from application.properties
	 */
	// boot/greeting/app-config-value
	@GetMapping("/app-config-value")
	public String appConfigValue() {
		return applicationValue + " " + arrayNumber;
	}
}