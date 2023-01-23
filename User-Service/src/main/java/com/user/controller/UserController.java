package com.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.user.dto.UserDto;
import com.user.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController<T>{

	@Autowired
	private UserService<T> userService;
	
	@GetMapping
	public T getUsers(@RequestParam(required = false) String email) throws JsonMappingException, JsonProcessingException {
		return userService.getAllUsers(email);
	}
	
	@PostMapping
	public T addUser(@RequestBody UserDto userDto) {
		return userService.AddUser(userDto);
	}
	
	@GetMapping("/{id}")
	public T GetUserBYId(@PathVariable Integer id) {
		return userService.getUserById(id);
	}
}
