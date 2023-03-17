package com.auth.services;

import com.auth.payloads.LoginRequest;

public interface LoginService<T> {

	T login(LoginRequest request);
	
}
