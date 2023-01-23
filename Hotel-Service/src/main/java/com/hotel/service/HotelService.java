package com.hotel.service;

import java.util.List;

import com.hotel.dto.HotelDto;
import com.hotel.helper.ApiResponse;

public interface HotelService {

	ApiResponse addHotel(HotelDto hotelDto);
	
	ApiResponse getHotelById(Integer hid);
	
	ApiResponse getHotels(String location);
	
}
