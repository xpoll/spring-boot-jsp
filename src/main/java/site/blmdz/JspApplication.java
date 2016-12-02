package site.blmdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import site.blmdz.config.GlobalExceptionHandler;
import site.blmdz.config.ShiroConfiguration;
import site.blmdz.config.WebConfiguration;

@SpringBootApplication
@Import({
	GlobalExceptionHandler.class,
	ShiroConfiguration.class,
	WebConfiguration.class
	})
public class JspApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(JspApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(JspApplication.class);
		application.run(args);
	}
}
