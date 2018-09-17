package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.exception.UserNotFoundException;
import com.example.springboot2sandbox.repository.UserRepository;
import com.example.springboot2sandbox.service.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserResponse get(Integer id) {
		return userRepository.findById(id)
				.map(UserResponse::of)
				.orElseThrow(UserNotFoundException::new);
	}
}
