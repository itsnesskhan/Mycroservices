package com.auth.payloads;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

	@Id
	private String uid;
	private String name;
	private String email;
	private String password;
	
//	private List<Rating> ratings = new ArrayList<>();
	
}
