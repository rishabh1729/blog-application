package com.rishabh.springblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationResponse {

	private String username;
	
	private String authenticationToken;
}
