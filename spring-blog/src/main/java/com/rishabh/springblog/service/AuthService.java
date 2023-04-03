package com.rishabh.springblog.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rishabh.springblog.dto.AuthenticationResponse;
import com.rishabh.springblog.dto.LoginRequest;
import com.rishabh.springblog.dto.RegisterRequest;
import com.rishabh.springblog.model.UserInfo;
import com.rishabh.springblog.repository.UserRepository;
import com.rishabh.springblog.security.JwtProvider;

@Service
public class AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;

	public void signup(RegisterRequest registerRequest) {
		UserInfo user = new UserInfo();
		user.setUserName(registerRequest.getUsername());
		user.setPassword(encodePassword(registerRequest.getPassword()));
		user.setEmail(registerRequest.getEmail());

		userRepository.save(user);
	}

	private String encodePassword(String password) {
		return passwordEncoder.encode(password);
	}

	public AuthenticationResponse login(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new AuthenticationResponse(loginRequest.getUsername(), jwtProvider.generateToken(authentication));
	}

	public Optional<User> getCurrentUser() {
		User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return Optional.of(principal);
	}
}
