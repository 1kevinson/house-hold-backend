package com.project.household.api.Configuration;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.household.api.Entity.Admin;
import com.project.household.api.Entity.Owner;
import com.project.household.api.Entity.Request;
import com.project.household.api.Entity.Tenant;
import com.project.household.api.Exception.ErrorException;
import com.project.household.api.Repositiory.RequestRepository;
import com.project.household.api.Repositiory.UserRepository;

@Configuration
class LoadDatabase {

	private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
	private PasswordEncoderGenerator passGen = new PasswordEncoderGenerator();
	Request req = new Request();

	@Bean
	CommandLineRunner initDatabase(UserRepository userRepository, RequestRepository requestRepository) {
		req.setContent("Content");
		req.setDate(new Date());
		req.setUser(userRepository.findById(1).orElseThrow(() -> new ErrorException("user not found")));
		return args -> {
			// Load Users
			log.info("Preloading... " + userRepository
					.save(new Tenant("Arsene", "Kevin", "kevin@you.fr", passGen.encodePassword("pass_bizarre"))));
			log.info("Preloading... " + userRepository
					.save(new Owner("Natacha", "Sama", "natsama@yahoo.fr", passGen.encodePassword("pass_natou"))));
			log.info("Preloading... " + userRepository.save(new Admin()));

			// Load Requests
			log.info("Preloading... " + requestRepository.save(req));
		};
	}
}