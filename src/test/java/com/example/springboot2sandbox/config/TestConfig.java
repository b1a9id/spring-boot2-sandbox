package com.example.springboot2sandbox.config;

import com.example.springboot2sandbox.properties.*;
import org.springframework.boot.context.properties.*;
import org.springframework.boot.test.context.*;
import org.springframework.context.annotation.*;

@TestConfiguration
@Import(AppAppConfig.class)
@EnableConfigurationProperties(value = AppProperties.class)
public class TestConfig {
}
