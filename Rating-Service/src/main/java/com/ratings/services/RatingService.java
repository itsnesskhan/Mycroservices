package com.ratings.services;

import java.util.List;

import com.ratings.dto.RatingDto;
import com.ratings.helper.ApiResponse;

public interface RatingService {

	public ApiResponse addRating(RatingDto ratingDto);
	
	public List<RatingDto> getRatings();
	
	public ApiResponse getRatingss();
	
	public ApiResponse getRatingsById(Integer id);
	
	public ApiResponse getRatingsByHotelId(Integer hotelId0);
	
	public ApiResponse getRatingsByUserId(Integer userId);
	
	
}
