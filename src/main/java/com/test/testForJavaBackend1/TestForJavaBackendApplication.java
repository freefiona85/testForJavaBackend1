package com.test.testForJavaBackend1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.test.testForJavaBackend1" })
public class TestForJavaBackendApplication extends SpringBootServletInitializer {
	private static Class<TestForJavaBackendApplication> application = TestForJavaBackendApplication.class;

	public static void main(String[] args) {
		SpringApplication.run(TestForJavaBackendApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(application);
	}
}
