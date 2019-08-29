package com.example.springboot2sandbox.config;

import com.example.springboot2sandbox.properties.*;
import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
public class AppAppConfig {

	@Bean
	@ConfigurationProperties("app.app")
	public AppAppProperties appAppProperties() {
		return new AppAppProperties();
	}
}
