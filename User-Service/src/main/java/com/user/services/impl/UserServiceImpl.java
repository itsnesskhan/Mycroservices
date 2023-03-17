package com.user.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naseer.security.library.payloads.SharedData;
import com.user.dto.RatingDto;
import com.user.dto.UserDto;
import com.user.entity.User;
import com.user.feignClient.RatingClient;
import com.user.helper.ApiResponse;
import com.user.repository.UserRepository;
import com.user.services.UserService;

@Service
public class UserServiceImpl<T> implements UserService<T> {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RatingClient ratingClient;

	@Override
	public T AddUser(UserDto userDto) {
		User user = userRepository.findByEmail(userDto.getEmail());

		if (user != null) {
			return (T) new ApiResponse(null, "User Already Exist with email");
		}
		user = modelMapper.map(userDto, User.class);
		user = userRepository.save(user);
		return (T) new ApiResponse(modelMapper.map(user, UserDto.class), "SUCESS");
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getAllUsers(String email) throws JsonMappingException, JsonProcessingException {

		String token = SharedData.getSharedDataMap().get("jwtToken");
		
		if (email == null) {
			List<User> users = userRepository.findAll();
			System.out.println(users);

			if (!users.isEmpty()) {
				
				List<UserDto> userDtos = Arrays.asList(modelMapper.map(users, UserDto[].class));
				ObjectMapper objectMapper = new ObjectMapper();
				
				List data =	(List) ratingClient.getRatings(token).getBody().getData();
				List<RatingDto> dtos = (List<RatingDto>) data.stream().map(t -> {
					return  objectMapper.convertValue(t, RatingDto.class);
				}).collect(Collectors.toList());
				
//				 Deserializing JSON to List<RatingDto>
//				Object data = ratingClient.getRatingss().getBody().getData();	
//				first convert object into json
//				ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
//				List<RatingDto> readValue = objectMapper.readValue(ow.writeValueAsString(data), new TypeReference<List<RatingDto>>() {});
//				List<RatingDto> dtos = (List<RatingDto>) readValue.stream().map(t -> (RatingDto)t).collect(Collectors.toList());
				 
			
				for (RatingDto ratingDto : dtos) {

					for (UserDto userDto : userDtos) {
						
						if (userDto.getUid().equals(ratingDto.getUserId())) {
							
							userDto.getRatings().add(ratingDto);
						}
					}
				}
				
				return (T) new ApiResponse(userDtos, "SUCCESS");
			} else {
				return (T) new ApiResponse("NO DATA FOUND");
			}
		} else {
			User user = userRepository.findByEmail(email);

			if (user != null) {
				return (T) new ApiResponse(user, "SUCCESS");
			} else {
				return (T) new ApiResponse("NO DATA FOUND");

			}
		}
	}

	@Override
	public T getUserById(Integer id) {
		
		String token = SharedData.getSharedDataMap().get("jwtToken");
		
		Optional<User> user = userRepository.findById(id);

		if (user.isPresent()) {
			ArrayList<RatingDto> ratingDtos = (ArrayList<RatingDto>) ratingClient.getRatingsByUserId(id,token).getBody().getData();
			UserDto userDto = modelMapper.map(user, UserDto.class);
			userDto.setRatings(ratingDtos);
			return (T) new ApiResponse(userDto, "SUCCESS");
		} else {
			return (T) new ApiResponse("NOT FOUND");
		}
	}

}
