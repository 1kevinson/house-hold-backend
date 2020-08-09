package com.project.household.api.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderGenerator {

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public String encodePassword(String password) {
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	public boolean verifyPassword(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
}
