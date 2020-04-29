package com.example.springboot2sandbox.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"バックアップ日", "ID", "name"})
public class BrandDto {
	@JsonProperty("バックアップ日")
	private String backupDate;
	@JsonProperty("ID")
	private Integer id;
	@JsonProperty("name")
	private String name;
}
