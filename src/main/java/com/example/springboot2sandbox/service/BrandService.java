package com.example.springboot2sandbox.service;

import com.example.springboot2sandbox.entity.Brand;
import com.example.springboot2sandbox.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BrandService {

	private final BrandRepository brandRepository;

	public List<Brand> getBrands() {
		return brandRepository.findAll();
	}

	public Optional<Brand> getBrand(Integer id) {
		return brandRepository.findById(id);
	}
}
