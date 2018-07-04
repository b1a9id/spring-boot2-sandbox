package com.example.springboot2sandbox.entity;

import com.example.springboot2sandbox.enums.Gender;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Brand implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	public Brand(String name, Gender gender) {
		this.name = name;
		this.gender = gender;
	}
}
