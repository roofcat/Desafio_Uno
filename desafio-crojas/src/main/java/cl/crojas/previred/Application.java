package cl.crojas.previred;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 
 * @author Christian Rojas N.
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "cl.crojas.*")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
