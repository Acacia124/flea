package site.acacia.flea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author 张胤
 *
 *         2018年8月28日-上午9:26:00
 */
@EnableScheduling
@ComponentScan({ "site.acacia.flea" })
@SpringBootApplication
public class App extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(App.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
