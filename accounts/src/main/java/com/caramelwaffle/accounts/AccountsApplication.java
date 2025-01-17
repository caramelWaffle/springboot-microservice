package com.caramelwaffle.accounts;

import com.caramelwaffle.accounts.model.AccountContactInfoData;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts microservice REST API Documentation",
				description = "WaffleBank Accounts microservice REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "CH Hoho",
						email = "tanachart.ar@gmail.com",
						url = "https://github.com/caramelWaffle"
				),
				license = @License(
						name = "Apache 2.0"
				)
		)
)
@EnableConfigurationProperties(value = AccountContactInfoData.class)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
