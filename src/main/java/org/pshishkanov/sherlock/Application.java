package org.pshishkanov.sherlock;

import org.pshishkanov.sherlock.security.model.Role;
import org.pshishkanov.sherlock.security.model.Account;
import org.pshishkanov.sherlock.security.repository.AccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by pshishkanov on 12/04/15.
 */

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(final AccountRepository accountRepository) {
        String username = "admin";
        return strings -> {
            if (accountRepository.findByUsername(username) == null) {
                accountRepository.save(new Account(username, new BCryptPasswordEncoder().encode(username), Role.ROLE_ADMIN));
            }
        };
    }
}

