package com.cluster9.logDispatcherRestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
// import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;

@SpringBootApplication
public class LogDispatcherRestServiceApplication {
	
	//@Autowired
	static WebLogParagraphRepo repo;

	
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(LogDispatcherRestServiceApplication.class, args);
		// System.out.println("bean definitions : " + Arrays.toString( app.getBeanDefinitionNames()));
		
	}

}
