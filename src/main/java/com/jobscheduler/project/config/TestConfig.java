package com.jobscheduler.project.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.repositories.UserRepository;

@Configuration
@Profile("Test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User(null, "Clara Almeida", "claraalma@gmail.com", "55998745321", "h54fiw21u");
		User user2 = new User(null, "Ricardo Peixoto", "rikapeix@gmail.com", "11997524536", "123ABCDs456");
		User user3 = new User(null, "Ana Silva", "anasilvafr@gmail.com", "16987451265", "123qiud");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
	}
}


