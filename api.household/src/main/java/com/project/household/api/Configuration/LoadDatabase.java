package com.project.household.api.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.project.household.api.Entity.User;
import com.project.household.api.Enumeration.UserRole;
import com.project.household.api.Repositiory.UserRepository;

@Configuration
class LoadDatabase {

  private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading... " + repository.save(new User("Arsene", "Kevin",UserRole.ADMIN.getEnumString(),"kevin@you.fr","pass_bizarre")));
    };
  }
}