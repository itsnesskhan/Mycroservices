package com.ratings.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ratings.dto.HotelDto;
import com.ratings.dto.RatingDto;
import com.ratings.feignclient.HotelClient;
import com.ratings.helper.ApiResponse;
import com.ratings.models.Rating;
import com.ratings.repository.RatingRepository;
import com.ratings.services.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private HotelClient hotelClient;
	
	@Override
	public ApiResponse addRating(RatingDto ratingDto) {
		Rating rating = ratingRepository.save(modelMapper.map(ratingDto, Rating.class));
		return new ApiResponse(modelMapper.map(rating, RatingDto.class), "SUCCESS");
	}

	@Override
	public List<RatingDto> getRatings() {
		List<Rating> ratings = ratingRepository.findAll();
		return ratings.stream().map(rating-> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public ApiResponse getRatingss() {
		List<Rating> ratings = ratingRepository.findAll();
		List<RatingDto> ratingDtos = ratings.stream().map(rating-> modelMapper.map(rating, RatingDto.class)).collect(Collectors.toList());
		ratingDtos.forEach(rating-> {
			ApiResponse hotelResponse = hotelClient.getHotelById(rating.getHotelId());
			System.out.println(hotelResponse);
			
			Object data = hotelResponse.getData();
			HotelDto hotelDto = new ObjectMapper().convertValue(data, HotelDto.class);
			
			rating.setHotel(hotelDto);
		});
		return new ApiResponse(ratingDtos, "SUCCESS");
		
	}

	@Override
	public ApiResponse getRatingsById(Integer id) {
		Optional<Rating> rating = ratingRepository.findById(id);
		return new ApiResponse(rating, "SUCCESS");
	}

	@Override
	public ApiResponse getRatingsByHotelId(Integer hotelId0) {
		List<Rating> hotelRatings = ratingRepository.findByHotelId(hotelId0);
		List<RatingDto> ratings = Arrays.asList(modelMapper.map(hotelRatings, RatingDto[].class));
		ratings.forEach(rating-> {
			ApiResponse hotelResponse = hotelClient.getHotelById(rating.getHotelId());
			System.out.println(hotelResponse);
			
			Object data = hotelResponse.getData();
			HotelDto hotelDto = new ObjectMapper().convertValue(data, HotelDto.class);
			
			rating.setHotel(hotelDto);
		});
		return  new ApiResponse(ratings, "SUCCESS");
	}

	@Override
	public ApiResponse getRatingsByUserId(Integer userId) {
		List<Rating> userRatings = ratingRepository.findByUserId(userId);
		List<RatingDto> ratings = Arrays.asList(modelMapper.map(userRatings, RatingDto[].class));
		ratings.forEach(rating-> {
			ApiResponse hotelResponse = hotelClient.getHotelById(rating.getHotelId());
			System.out.println(hotelResponse);
			
			Object data = hotelResponse.getData();
			HotelDto hotelDto = new ObjectMapper().convertValue(data, HotelDto.class);
			
			rating.setHotel(hotelDto);
		});
		return  new ApiResponse(ratings, "SUCCESS");
	}
	
	

}
