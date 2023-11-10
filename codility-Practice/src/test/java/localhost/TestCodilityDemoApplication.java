package localhost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestCodilityDemoApplication {

	public static void main(String[] args) {
		SpringApplication.from(CodilityDemoApplication::main).with(TestCodilityDemoApplication.class).run(args);
	}

}
