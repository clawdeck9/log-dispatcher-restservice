package com.cluster9.logDispatcherRestService;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import com.cluster9.logDispatcherRestService.dao.LogParagraphRepo;
// import com.cluster9.logDispatcherRestService.entities.LogParagraph;

@SpringBootApplication
public class LogDispatcherRestServiceApplication {
	
	//@Autowired
	static LogParagraphRepo repo;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(LogDispatcherRestServiceApplication.class, args);
		// System.out.println("bean definitions : " + Arrays.toString( app.getBeanDefinitionNames()));
		
	}
}
