package com.jobscheduler.project.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jobscheduler.project.entities.Category;
import com.jobscheduler.project.entities.Client;
import com.jobscheduler.project.entities.Job;
import com.jobscheduler.project.entities.JobOrder;
import com.jobscheduler.project.entities.Location;
import com.jobscheduler.project.entities.OrderItem;
import com.jobscheduler.project.entities.Payment;
import com.jobscheduler.project.entities.User;
import com.jobscheduler.project.entities.enums.OrderStatus;
import com.jobscheduler.project.repositories.CategoryRepository;
import com.jobscheduler.project.repositories.ClientRepository;
import com.jobscheduler.project.repositories.JobOrderRepository;
import com.jobscheduler.project.repositories.JobRepository;
import com.jobscheduler.project.repositories.LocationRepository;
import com.jobscheduler.project.repositories.OrderItemRepository;
import com.jobscheduler.project.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JobOrderRepository jobOrderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category ct1 = new Category(null, "Maintenance");
		Category ct2 = new Category(null, "Training");
		Category ct3 = new Category(null, "Programming");
		Category ct4 = new Category(null, "Support");
		Category ct5 = new Category(null, "Projects");
		
		categoryRepository.saveAll(Arrays.asList(ct1, ct2, ct3, ct4, ct5));
		
		User user1 = new User(null, "Clara Almeida", "claraalma@gmail.com", "55998745321", "h54fiw21u");
		User user2 = new User(null, "Ricardo Peixoto", "rikapeix@gmail.com", "11997524536", "123ABCDs456");
		User user3 = new User(null, "Ana Silva", "anasilvafr@gmail.com", "16987451265", "123qiud");
		User user4 = new User(null, "Carlos Santos", "carlosantosrp@gmail.com", "21997850123", "123qiud");
		
		userRepository.saveAll(Arrays.asList(user1, user2, user3, user4));

		Location loc1 = new Location(null, "Street 34th", "456", "Jacaranda Alameda", "North Carolina", "next to Mc Donalds", "Saint Leo", "40042120");
		Location loc2 = new Location(null, "Street 17th", "125", "Downtown", "North Carolina", "opposite St Lourdes Churh", "Saint Leo", "40042510");
		Location loc3 = new Location(null, "Principal Avenue", "52", "", "North Carolina", "next to Mc Donalds", "Saint Leo", "40043010");
		Location loc4 = new Location(null, "Palm road", "132", "Palm Springs", "California", "", "Los Angeles", "10142200");
		Location loc5 = new Location(null, "Street 8th", "140", "Downtown", "California", "", "Los Angeles", "12045210");
		
		locationRepository.saveAll(Arrays.asList(loc1, loc2, loc3, loc4, loc5));
		
		Client cli1 = new Client(null, "Dell Inc.", loc1, "932849324293", "");
		Client cli2 = new Client(null, "Airplay Tech", loc2, "87324283943", "");
		Client cli3 = new Client(null, "John Doe", loc3, "", "39328479234");
		Client cli4 = new Client(null, "Phill Collins", loc4, "", "13124332552");
		Client cli5 = new Client(null, "Frank Zappa", loc5, "", "461523465");
		
		clientRepository.saveAll(Arrays.asList(cli1, cli2, cli3, cli4, cli5));
		
		JobOrder jo1 = new JobOrder(null, Instant.parse("2019-06-20T19:15:07Z"), OrderStatus.PAYMENT_CONCLUDED, user1, cli1);
		JobOrder jo2 = new JobOrder(null, Instant.parse("2019-07-21T04:32:10Z"), OrderStatus.PAYMENT_CONCLUDED, user2, cli2);
		JobOrder jo3 = new JobOrder(null, Instant.parse("2019-07-22T21:21:22Z"), OrderStatus.DELIVERED, user1, cli3); 
		JobOrder jo4 = new JobOrder(null, Instant.parse("2020-01-11T21:21:22Z"), OrderStatus.DELIVERED, user3, cli4);  
		JobOrder jo5 = new JobOrder(null, Instant.parse("2020-08-05T21:21:22Z"), OrderStatus.PAYMENT_ONAPPROVAL, user4, cli4); 
		
		jobOrderRepository.saveAll(Arrays.asList(jo1, jo2, jo3, jo4));
		
		Job j1 = new Job(null, "Web Maintenance", "Lorem ipsum dolor sit amet, consect.", 8, 400.00, 50.00, ""); 
		Job j2 = new Job(null, "Software Support", "Nulla eu imperdit purus. Maecenas an.", 16, 800.00, 50.00, ""); 
		Job j3 = new Job(null, "Design Training", "Nam eleifend maximus tortor, at mols.", 8, 600.00, 75.00, ""); 
		Job j4 = new Job(null, "CyberSecurity Training", "Donec aliquet odio ac roncus cursus.", 24, 2160.00, 90.00, ""); 
		Job j5 = new Job(null, "Software creation", "Cras fringilla convalis sem vel faucbus.", 80, 6400.00, 80.00, "");
		Job j6 = new Job(null, "Infrastructure + server project", "Cras fringilla convalis sem vel faucbus.", 120, 13800.00, 115.00, "");
		
		jobRepository.saveAll(Arrays.asList(j1, j2, j3, j4, j5, j6));
		
		j1.getCategories().add(ct1);
		j2.getCategories().add(ct3);
		j2.getCategories().add(ct4);
		j3.getCategories().add(ct2);
		j4.getCategories().add(ct2);
		j4.getCategories().add(ct3);
		j5.getCategories().add(ct3);
		j5.getCategories().add(ct5);
		j6.getCategories().add(ct5);
		
		jobRepository.saveAll(Arrays.asList(j1, j2, j3, j4, j5, j6));

		OrderItem orderItem1 = new OrderItem(jo1, j1, 2, j1.getPrice());
		OrderItem orderItem2 = new OrderItem(jo1, j2, 1, j2.getPrice());
		OrderItem orderItem3 = new OrderItem(jo2, j2, 3, j2.getPrice());
		OrderItem orderItem4 = new OrderItem(jo3, j3, 2, j3.getPrice());
	
		orderItemRepository.saveAll(Arrays.asList(orderItem1, orderItem2, orderItem3, orderItem4));

		Payment payment1 = new Payment(null, Instant.parse("2019-06-20T19:15:07Z"), jo1);
		jo1.setPayment(payment1);
		Payment payment2 = new Payment(null, Instant.parse("2019-07-21T04:32:10Z"), jo2);
		jo2.setPayment(payment2);
		
		jobOrderRepository.saveAll(Arrays.asList(jo1, jo2));
		
	}
}


