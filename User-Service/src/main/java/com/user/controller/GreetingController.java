package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.helper.MyProperties;

@RestController
@RequestMapping("/welcome")
@RefreshScope  //refresh scope mostly work with configproperties
public class GreetingController {

	@Value("${my.greetings: default msg}")
	private String message;
	
	@Autowired
	private MyProperties myProperties;
	
	@RequestMapping
	public String welcomeHandler() {
		return message+" "+myProperties.getName();
	}
}
