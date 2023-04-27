package bankManagement.accountService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import bankManagement.accountService.configuration.RsaKeyProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class) //allow us to register annotated class @ConfigurationProperties as Bean (instead of Component)
public class ApplicationBankManagement {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationBankManagement.class, args);
	}
}
