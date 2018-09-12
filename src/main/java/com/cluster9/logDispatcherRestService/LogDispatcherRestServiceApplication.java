package com.cluster9.logDispatcherRestService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.ApplicationContext;

import com.cluster9.logDispatcherRestService.dao.LogParagraphRepo;
// import com.cluster9.logDispatcherRestService.entities.LogParagraph;

@SpringBootApplication
public class LogDispatcherRestServiceApplication {
	
	//@Autowired
	static LogParagraphRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(LogDispatcherRestServiceApplication.class, args);
		
	}
}


//il faut supprimer le repo car il n'est pas accessible de cette manirèe lorsqu'il est auto généré par Spr data rest à partir d'une entité
//
//comment écrire dans le dao ? il faut trouver un objet dans le context. Mais quoi? créer un service?