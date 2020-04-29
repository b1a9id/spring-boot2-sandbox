package com.example.springboot2sandbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
	@JsonProperty("バックアップ日")
	private String backupDate;
	@JsonProperty("ID")
	private String id;
	@JsonProperty("name")
	private String name;
}
