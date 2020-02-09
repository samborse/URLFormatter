package neueda.urlformatter.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author Samadhan
 *
 */
@SpringBootApplication
public class NeuedaUrlFormaterApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeuedaUrlFormaterApplication.class, args);
	}
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NeuedaUrlFormaterApplication.class);
    }
}
