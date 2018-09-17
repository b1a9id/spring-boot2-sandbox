package com.example.springboot2sandbox.service.dto.response;

import com.example.springboot2sandbox.entity.User;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Value
public class UserResponse implements Serializable {
	private String name;

	private String username;

	private LocalDateTime createdAt;

	private LocalDateTime lastSignInAt;

	private UserResponse(User user) {
		this.name = user.getName();
		this.username = user.getUsername();
		this.createdAt = user.getCreatedAt();
		this.lastSignInAt = user.getLastSignInAt();
	}

	public static UserResponse of(User user) {
		return new UserResponse(user);
	}
}
