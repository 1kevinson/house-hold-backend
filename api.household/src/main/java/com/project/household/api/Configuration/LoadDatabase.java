package com.project.household.api.Configuration;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.javafaker.Faker;
import com.project.household.api.Entity.Owner;
import com.project.household.api.Entity.Tenant;
import com.project.household.api.Repository.Request.RequestRepository;
import com.project.household.api.Repository.User.UserRepository;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	private PasswordEncoderGenerator passGen = new PasswordEncoderGenerator();

	Faker faker = new Faker(new Locale("fr"));
	private int numberOfUsers = 15;

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, RequestRepository requestRepository) {

		return args -> { // Load Users
			for (int i = 1; i <= numberOfUsers; i++) {
				if (i < 10) {
					log.info("User " + i + " => "
							+ userRepository.save(new Tenant(faker.name().firstName(), faker.name().lastName(),
									faker.internet().emailAddress(), passGen.encodePassword("pass_word"))));
				} else {
					log.info("User " + i + " => "
							+ userRepository.save(new Owner(faker.name().firstName(), faker.name().lastName(),
									faker.internet().emailAddress(), passGen.encodePassword("pass_word"))));
				}
			}
		};
	}

}