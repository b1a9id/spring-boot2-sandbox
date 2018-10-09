package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.entity.User;
import com.example.springboot2sandbox.enums.Role;
import com.example.springboot2sandbox.service.dto.request.UserRequest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Sql(scripts = {"classpath:/db/migration/drop.sql", "classpath:/db/migration/initial.sql"})
class UserServiceTest {

	@Autowired
	private UserService userService;

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "ADMIN")
	void list1ForAdmin() {
		Assertions.assertThat(userService.list1()).hasSize(2);
	}

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "STAFF")
	void list1ForStaff() {
		Assertions.assertThatThrownBy(() -> userService.list1())
				.isInstanceOf(AccessDeniedException.class);
	}

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "ADMIN")
	void list2ForAdmin() {
		Assertions.assertThat(userService.list2(Role.ADMIN.name())).hasSize(2);
	}

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "STAFF")
	void list2ForStaff() {
		Assertions.assertThatThrownBy(() -> userService.list2(Role.STAFF.name()))
				.isInstanceOf(AccessDeniedException.class);
	}

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "ADMIN")
	void list3ForAdmin() {
		UserRequest request = new UserRequest();
		request.setName("ruchitate");
		Assertions.assertThat(userService.list3(request)).hasSize(2);
	}

	@DisplayName("@PreAuthorizeのテスト")
	@Test
	@WithMockUser(roles = "STAFF")
	void list3ForStaff() {
		UserRequest request = new UserRequest();
		request.setName("test");
		Assertions.assertThatThrownBy(() -> userService.list3(request))
				.isInstanceOf(AccessDeniedException.class);
	}

	@DisplayName("@PostAuthorizeのテスト")
	@Test
	@WithMockUser
	void get1Success() {
		Assertions.assertThat(userService.get1(1))
				.extracting(User::getUsername)
				.isEqualTo("ruchitate");
	}

	@DisplayName("@PostAuthorizeのテスト")
	@Test
	@WithMockUser
	void get1AccessDenied() {
		Assertions.assertThatThrownBy(() -> userService.get1(3))
				.isInstanceOf(AccessDeniedException.class);
	}

	@DisplayName("@PreFilterのテスト")
	@Test
	@WithMockUser
	void list4() {
		UserRequest request1 = new UserRequest();
		request1.setName("ruchitate");
		UserRequest request2 = new UserRequest();
		request2.setName("test");
		List<UserRequest> requests = new ArrayList<>(Arrays.asList(request1, request2));

		Assertions.assertThat(userService.list4(requests))
				.extracting(User::getId, User::getUsername)
				.containsOnly(Tuple.tuple(1, "ruchitate"));
	}

	@DisplayName("@PostFilterのテスト")
	@Test
	@WithMockUser
	void list5() {
		Assertions.assertThat(userService.list5())
				.extracting(User::getUsername)
				.containsOnly("ruchitate");
	}

}
