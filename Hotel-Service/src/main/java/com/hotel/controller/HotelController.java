package com.hotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.dto.HotelDto;
import com.hotel.helper.ApiResponse;
import com.hotel.service.HotelService;

@RestController
@RequestMapping("/api/v1/hotel")
public class HotelController {

	@Autowired
	private HotelService hotelService;
	
	@PostMapping
	public ApiResponse addHotel(@RequestBody HotelDto hotelDto) {
		return hotelService.addHotel(hotelDto);
	}
	
	@GetMapping
	public ApiResponse getHotel(@RequestParam(required = false) String location) {
		return hotelService.getHotels(location);
	}
	
	@GetMapping("/{id}")
	public ApiResponse getHotelById(@PathVariable Integer id) {
		System.out.println("get hotel by id "+id);
		return hotelService.getHotelById(id);
	}
}
