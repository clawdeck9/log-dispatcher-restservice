package com.cluster9.logDispatcherRestService;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cluster9.logDispatcherRestService.dao.AppRoleRepo;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.AppRole;
import com.cluster9.logDispatcherRestService.entities.AppUser;
import com.cluster9.logDispatcherRestService.service.AccountServiceImpl;

@SpringBootApplication
public class LogDispatcherRestServiceApplication implements CommandLineRunner {
	
	//@Autowired
	static WebLogParagraphRepo repo;
	@Autowired
	private AccountServiceImpl asi;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(LogDispatcherRestServiceApplication.class, args);
		// System.out.println("bean definitions : " + Arrays.toString( app.getBeanDefinitionNames()));
		
	}
	
	@Bean
	BCryptPasswordEncoder getBCEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void run(String... arg0) throws Exception {
		// creates some user with their roles
//		TODO: add some constraints to the user<=>role relationship
		
		System.out.println("db init: no user or role were added, function disabled");
//		AppRole userRole = new AppRole();
//		userRole.setRoleName("USER");
//		
//		AppUser userOne = new AppUser();
//		userOne.setUsername("user");
//		userOne.setPassword("pass");
//		
//		asi.saveUser(userOne);
//		asi.saveRole(userRole);
//		asi.addRoleToUser("user", "USER");
	}
	
}
