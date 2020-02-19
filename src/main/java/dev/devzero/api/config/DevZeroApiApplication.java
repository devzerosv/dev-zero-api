package dev.devzero.api.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({ "dev.devzero.api*" })
@EntityScan(basePackages = { "dev.devzero.api.model" })
@EnableJpaRepositories("dev.devzero.api.repository")
public class DevZeroApiApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DevZeroApiApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DevZeroApiApplication.class, args);
	}

}
