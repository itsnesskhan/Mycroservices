package com.hotel.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.hibernate.engine.query.spi.ReturnMetadata;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.dto.HotelDto;
import com.hotel.helper.ApiResponse;
import com.hotel.models.Hotel;
import com.hotel.repository.HotelRepository;
import com.hotel.service.HotelService;

import net.bytebuddy.asm.Advice.Return;

@Service
public class HotelServiceImpl<T> implements HotelService {

	@Autowired
	private HotelRepository hotelRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public ApiResponse addHotel(HotelDto hotelDto) {
		Hotel hotel = hotelRepository.save(modelMapper.map(hotelDto, Hotel.class));
		return new ApiResponse(hotel, "SUCCESS");
	}

	@Override
	public ApiResponse getHotelById(Integer hid) {
		Optional<Hotel> hotel = hotelRepository.findById(hid);
		if (hotel.isPresent()) {
			return new ApiResponse(modelMapper.map(hotel.get(), HotelDto.class), "SUCCESS");
		} else {
			return new ApiResponse("NOT FOUND");
		}
	}

	@Override
	public ApiResponse getHotels(String location) {
		if (location == null) {
			List<Hotel> hotels = hotelRepository.findAll();
			if (!hotels.isEmpty()) {
				return new ApiResponse(Arrays.asList(modelMapper.map(hotels, HotelDto[].class)),"SUCCESS");
			} else {
				return new ApiResponse("NOT FOUND");
			}
		} else {
			List<Hotel> hotels = hotelRepository.findByLocation(location);
			if (!hotels.isEmpty()) {
				return new ApiResponse(hotels, "SUCESS");
			} else {
				return new ApiResponse("NOT FOUND");
			}
		}
	}

}
