package com.user.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

	private Integer uid;
	
	private String name;
	
	private String email;
	
	private String password;
	
	private List<RatingDto> ratings = new ArrayList<>();
}
