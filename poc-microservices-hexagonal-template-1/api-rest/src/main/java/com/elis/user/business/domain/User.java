package com.elis.user.business.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor 
@NoArgsConstructor
public class User {

	private Long id;

	private String username;

	private String email;

	private String password;

	private String address;

	private int age;
	

}
