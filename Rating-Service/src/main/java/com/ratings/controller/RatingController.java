package com.ratings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratings.dto.RatingDto;
import com.ratings.helper.ApiResponse;
import com.ratings.services.RatingService;

@RestController
@RequestMapping("/api/v1/rating")
public class RatingController {
	
	@Autowired
	private RatingService ratingService;
	
	@PostMapping
	public ResponseEntity<ApiResponse> addRating(@RequestBody RatingDto ratingDto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.addRating(ratingDto));
	}
	
//	@GetMapping
//	public ResponseEntity<List<RatingDto>> getRatings() {
//		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatings());
//	}
	
	@GetMapping
	public ResponseEntity<ApiResponse> getRatings() {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingss());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getRatingsById(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsById(id));
	}
	
	@GetMapping("/hotel/{id}")
	public ResponseEntity<ApiResponse> getRatingsByhotelId(@PathVariable Integer id) {
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByHotelId(id));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<ApiResponse> getRatingsByUserId(@PathVariable Integer id) {
		System.out.println("getting retting by hotel id "+id);
		return ResponseEntity.status(HttpStatus.OK).body(ratingService.getRatingsByUserId(id));
	}
}
