package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.entity.Brand;
import com.example.springboot2sandbox.enums.Gender;
import com.example.springboot2sandbox.repository.BrandRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BrandServiceTest {

	@Autowired
	private BrandService brandService;

	@MockBean
	private BrandRepository brandRepository;

	@Nested
	class GetBrandsTest {
		@Test
		void success() {
			List<Brand> brands = Arrays.asList(
					createBrand("STOF", Gender.UNISEX), createBrand("bedsidedrama", Gender.WOMAN));
			given(brandRepository.findAll()).willReturn(brands);
			Assertions.assertThat(brandService.getBrands()).hasSize(2);
		}

		@Test
		void sizeZero() {
			given(brandRepository.findAll()).willReturn(Collections.emptyList());
			Assertions.assertThat(brandService.getBrands()).hasSize(0);
		}
	}

	private Brand createBrand(String name, Gender gender) {
		return new Brand(name, gender);
	}
}