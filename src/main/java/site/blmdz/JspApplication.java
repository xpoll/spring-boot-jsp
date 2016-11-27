package site.blmdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import site.blmdz.exception.GlobalExceptionHandler;

@SpringBootApplication
@Import({
	GlobalExceptionHandler.class,//异常
	ShiroConfiguration.class//权限
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
