package com.example.springboot2sandbox.contoroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("sample")
public class FileUploadController {

	@GetMapping
	public String index() {
		return "index";
	}

	@PostMapping("upload")
	public String upload(
			@RequestParam("upload_file") MultipartFile multipartFile) {
		System.out.println(multipartFile);
		return "redirect:/sample";
	}
}
