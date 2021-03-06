package app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//Класс запуска тут происходит запуск)
//С этого все начинается. В теории, заканчивается все тоже тут.
@SpringBootApplication
public class SpringBootLab4Web extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootLab4Web.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLab4Web.class, args);
	}
}
