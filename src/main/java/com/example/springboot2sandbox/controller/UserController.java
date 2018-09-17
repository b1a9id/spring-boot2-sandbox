package com.example.springboot2sandbox.controller;

import com.example.springboot2sandbox.service.UserService;
import com.example.springboot2sandbox.service.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("{id}")
	public UserResponse get(@PathVariable Integer id) {
		return userService.get(id);
	}
}
