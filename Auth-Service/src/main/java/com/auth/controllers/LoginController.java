package com.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class LoginController<T> {
	
	@Autowired
	private com.auth.services.LoginService loginService;

	@PostMapping("/login")
	public T doLogin( @RequestBody com.auth.payloads.LoginRequest request ) {
		
		Object login = this.loginService.login(request);
		return (T) login;
	}
	
}
