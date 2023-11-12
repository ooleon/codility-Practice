package localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHealthcheckRestFullApplication {

	public static void main(String[] args) {
		SpringApplication.from(HealthcheckRestFullApplication::main).with(TestHealthcheckRestFullApplication.class).run(args);
	}

}
