package com.example.springboot2sandbox;

import com.example.springboot2sandbox.config.*;
import com.example.springboot2sandbox.properties.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;
import org.springframework.test.context.junit.jupiter.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class}, initializers = ConfigFileApplicationContextInitializer.class)
class ConfigurationPropertyTest {

	@Autowired
	private AppProperties appProperties;

	@Test
	void getValue() {
		Assertions.assertThat(appProperties.getDomain())
				.isEqualTo("b1a9idps.com");
	}
}
