package com.naseer.security.library.jwt;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		log.error("Unauthorized error: {}", authException.getMessage());
		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		 response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		  final Map<String, Object> body = new HashMap();
	        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
	        body.put("error", "Unauthorized");
	        body.put("message", authException.getMessage());
	        body.put("path", request.getServletPath());
			
		        
		
	}

	

}
