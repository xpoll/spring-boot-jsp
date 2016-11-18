package site.blmdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import site.blmdz.exception.ExceptionResolver;

@SpringBootApplication
public class JspApplication extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JspApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JspApplication.class);
		application.run(args);
	}
	
	@Bean
	public ExceptionHandlerExceptionResolver exceptionResolver() {
		ExceptionResolver resolver = new ExceptionResolver();
		return resolver;
	}
}
