package com.jobscheduler.project.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.entities.enums.OrderStatus;
import com.jobscheduler.project.repositories.CategoryRepository;
import com.jobscheduler.project.repositories.JobOrderRepository;
import com.jobscheduler.project.repositories.UserRepository;

@Configuration
@Profile("Test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobOrderRepository jobOrderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category ct1 = new Category(null, "Maintenance");
		Category ct2 = new Category(null, "Training");
		Category ct3 = new Category(null, "Programming");
		Category ct4 = new Category(null, "Support");
		
		categoryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4));
		
		User user1 = new User(null, "Clara Almeida", "claraalma@gmail.com", "55998745321", "h54fiw21u");
		User user2 = new User(null, "Ricardo Peixoto", "rikapeix@gmail.com", "11997524536", "123ABCDs456");
		User user3 = new User(null, "Ana Silva", "anasilvafr@gmail.com", "16987451265", "123qiud");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3));
		
		JobOrder jo1 = new JobOrder(null, Instant.parse("2019-06-20T19:15:07Z"), OrderStatus.PAYMENT_CONCLUDED, user1);
		JobOrder jo2 = new JobOrder(null, Instant.parse("2019-07-21T04:32:10Z"), OrderStatus.PAYMENT_CONCLUDED, user2);
		JobOrder jo3 = new JobOrder(null, Instant.parse("2019-07-22T21:21:22Z"), OrderStatus.DELIVERED, user1); 
		
		jobOrderRepository.saveAll(Arrays.asList(jo1, jo2, jo3));
		
		
	}
}


