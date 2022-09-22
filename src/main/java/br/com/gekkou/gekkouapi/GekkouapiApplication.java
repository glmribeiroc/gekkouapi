package br.com.gekkou.gekkouapi;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.com.gekkou.gekkouapi.modules.account.entity.Account;
import br.com.gekkou.gekkouapi.modules.account.entity.Role;
import br.com.gekkou.gekkouapi.modules.account.service.AccountService;

@SpringBootApplication
public class GekkouapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GekkouapiApplication.class, args);
	}

	@Bean
	CommandLineRunner run(AccountService accountService) {
		return args -> {
			accountService.createRole(new Role(null, "ROLE_USER"));
			accountService.createRole(new Role(null, "ROLE_ADMIN"));
			accountService.createRole(new Role(null, "ROLE_SUPER_ADMIN"));
			accountService.createRole(new Role(null, "ROLE_SUPPORTER"));

			accountService.createAccount(new Account(null, "Guilherme", "guilherme@email.com", "123456", null,
					new ArrayList<>(), new Date()));

			accountService.addRoleToUser("guilherme@email.com", "ROLE_USER");
			accountService.addRoleToUser("guilherme@email.com", "ROLE_ADMIN");
			accountService.addRoleToUser("guilherme@email.com", "ROLE_SUPER_ADMIN");
		};
	}
}
