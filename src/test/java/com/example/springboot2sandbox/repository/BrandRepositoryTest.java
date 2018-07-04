package com.example.springboot2sandbox.repository;

import com.example.springboot2sandbox.entity.Brand;
import com.example.springboot2sandbox.enums.Gender;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
class BrandRepositoryTest {

	@Nested
	@DataJpaTest
	class FindByGender {

		@Autowired
		private TestEntityManager entityManager;

		@Autowired
		private BrandRepository brandRepository;

		@BeforeEach
		void beforeEach() {
			entityManager.persist(new Brand("STOF", Gender.UNISEX));
			entityManager.persist(new Brand("ETHOSENS", Gender.MAN));
			entityManager.persist(new Brand("dulcamara", Gender.UNISEX));
		}

		@Test
		void man() {
			List<Brand> brands = brandRepository.findByGender(Gender.MAN);
			org.assertj.core.api.Assertions.assertThat(brands)
					.extracting(Brand::getName, Brand::getGender)
					.containsExactly(Tuple.tuple("ETHOSENS", Gender.MAN));
		}

		@Test
		void woman() {
			List<Brand> brands = brandRepository.findByGender(Gender.WOMAN);
			org.assertj.core.api.Assertions.assertThat(brands)
					.hasSize(0);
		}
	}

	@Nested
	@DataJpaTest
	class CountByGender {
		@Autowired
		private TestEntityManager entityManager;

		@Autowired
		private BrandRepository brandRepository;

		@BeforeEach
		void beforeEach() {
			entityManager.persist(new Brand("STOF", Gender.UNISEX));
			entityManager.persist(new Brand("ETHOSENS", Gender.MAN));
			entityManager.persist(new Brand("dulcamara", Gender.UNISEX));
		}

		@Test
		void man() {
			int count = brandRepository.countByGender(Gender.MAN);
			Assertions.assertEquals(1, count);
		}

		@Test
		void woman() {
			int count = brandRepository.countByGender(Gender.WOMAN);
			Assertions.assertEquals(0, count);
		}
	}
}