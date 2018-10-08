package com.example.springboot2sandbox.controller;

import com.example.springboot2sandbox.enums.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
class UserControllerTest {
	@Nested
	@SpringBootTest
	@Sql(scripts = {"classpath:/db/migration/drop.sql", "classpath:/db/migration/initial.sql"})
	class Get {

		@Autowired
		private WebApplicationContext context;

		private MockMvc mockMvc;

		@BeforeEach
		void beforeEach() {
			mockMvc = MockMvcBuilders.webAppContextSetup(context)
					.apply(springSecurity())
					.build();
		}

		@DisplayName("MockHttpServletRequestBuilder#withを使ったユーザ指定。status=200,role=STAFF")
		@Test
		void useWith200() throws Exception {
			MvcResult result = mockMvc.perform(get("/users/{id}", 1)
					.with(user("ruchitate").roles(Role.STAFF.name())))
					.andExpect(status().isOk())
					.andReturn();
			assertEquals(
					"{\"name\":\"内立 良介\",\"username\":\"ruchitate\",\"createdAt\":\"2018-10-01T00:00:00\",\"lastSignInAt\":null}", result.getResponse().getContentAsString());
		}

		@DisplayName("MockHttpServletRequestBuilder#withを使ったユーザ指定。status=403,role=ADMIN")
		@Test
		void useWith403ForAdmin() throws Exception {
			mockMvc.perform(get("/users/{id}", 1)
					.with(user("ruchitate").roles(Role.ADMIN.name())))
					.andExpect(status().isForbidden())
					.andReturn();
		}

		@DisplayName("@WithMockUserを使ったユーザ指定。status=200,role=STAFF")
		@WithMockUser(username = "ruchitate", roles = "STAFF")
		@Test
		void useAnnotation200() throws Exception {
			MvcResult result = mockMvc.perform(get("/users/{id}", 1))
					.andExpect(status().isOk())
					.andReturn();
			assertEquals(
					"{\"name\":\"内立 良介\",\"username\":\"ruchitate\",\"createdAt\":\"2018-10-01T00:00:00\",\"lastSignInAt\":null}", result.getResponse().getContentAsString());
		}

		@DisplayName("@WithMockUserを使ったユーザ指定。status=403,role=ADMIN")
		@WithMockUser(username = "ruchitate", roles = "ADMIN")
		@Test
		void useAnnotation403ForAdmin() throws Exception {
			mockMvc.perform(get("/users/{id}", 1))
					.andExpect(status().isForbidden())
					.andReturn();
		}
	}

}
