package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.entity.User;
import com.example.springboot2sandbox.exception.UserNotFoundException;
import com.example.springboot2sandbox.repository.UserRepository;
import com.example.springboot2sandbox.service.dto.UserRequest;
import com.example.springboot2sandbox.service.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	public UserResponse get(Integer id) {
		return userRepository.findById(id)
				.map(UserResponse::of)
				.orElseThrow(UserNotFoundException::new);
	}

	@PreAuthorize("hasRole('ADMIN')")
	public List<User> list1() {
		return userRepository.findAll();
	}

	@PreAuthorize("#role == 'ADMIN'")
	public List<User> list2(String role) {
		return userRepository.findAll();
	}

	@PreAuthorize("#r.name == 'ruchitate'")
	public List<User> list3(@P("r") UserRequest request) {
		return userRepository.findAll();
	}

	@PostAuthorize("returnObject != null && returnObject.username == 'ruchitate'")
	public User get1(Integer id) {
		return userRepository.findById(id).orElse(null);
	}


}
