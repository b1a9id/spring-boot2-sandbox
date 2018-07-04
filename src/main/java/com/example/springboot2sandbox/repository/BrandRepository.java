package com.example.springboot2sandbox.repository;

import com.example.springboot2sandbox.entity.Brand;
import com.example.springboot2sandbox.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Integer> {

	Brand findByGender(Gender gender);

	Integer countByGender(Gender gender);
}
