package com.ratings.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.ratings.helper.ApiResponse;

//@FeignClient(name = "Hotel-Client",url  = "http://localhost:8081/api/v1/hotel")
@FeignClient(name = "HOTEL-SERVICE")
public interface HotelClient {

	@GetMapping("/api/v1/hotel/{id}")
	public ApiResponse getHotelById(@RequestHeader(value = "Authorization", required = true) String token ,@PathVariable Integer id);	
	
	
	
}
