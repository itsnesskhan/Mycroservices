package com.user.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.user.helper.ApiResponse;

//@FeignClient(name = "RATING-CLIENT", url = "http://localhost:8082/api/v1/rating")
@FeignClient(name = "RATING-SERVICE")
public interface RatingClient {
	
	
	@GetMapping("/api/v1/rating/user/{id}")
	public ResponseEntity<ApiResponse> getRatingsByUserId(@PathVariable Integer id);
	
//	@GetMapping
//	public ResponseEntity<List<RatingDto>> getRatings();
	
	@GetMapping("/api/v1/rating")
	public ResponseEntity<ApiResponse> getRatings();
	
}
