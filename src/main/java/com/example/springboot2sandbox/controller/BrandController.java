package com.example.springboot2sandbox.controller;

import com.example.springboot2sandbox.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("brands")
@RequiredArgsConstructor
public class BrandController {
	private final BrandService brandService;

	@GetMapping
	public String index() {
		return "index";
	}

	@PostMapping("upload")
	public String upload(
			@RequestParam("upload_file") MultipartFile multipartFile) {
		brandService.upload(multipartFile);
		return "redirect:/brands";
	}
}
