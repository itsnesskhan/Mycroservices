package com.user.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.user.dto.UserDto;

public interface UserService<T> {

	T AddUser(UserDto userDto);
	
	T getAllUsers(String email) throws JsonMappingException, JsonProcessingException;
	
	T getUserById(Integer id);
	
}
