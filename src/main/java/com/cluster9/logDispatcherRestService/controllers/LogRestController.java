package com.cluster9.logDispatcherRestService.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;


//@RefreshScope
@RestController
public class LogRestController {
	
	
	@Autowired
	private WebLogParagraphRepo repo;
	
	int number = (int) (Math.random()*100);

	@RequestMapping("/logs")
	public List<WebLogParagraph>  logs(){
		System.out.println("restcontroller::logs method - restcontroller number= " + number);
		return repo.findAll();
	}

	@RequestMapping("/logs/{tag}")
	public Page<WebLogParagraph>  logsByTag(@PathVariable String tag, @NotNull final Pageable pageable){
		System.out.println("restcontroller::logsByTags method - restcontroller number= " + number);
		System.out.println("restcontroller::logsByTags method - pageable: " + pageable.getPageSize() + " number: " + pageable.getPageNumber());
		return repo.logParagraphByTag(tag, pageable );
	}
//	public Page<YourEntityHere> readPageable(@NotNull final Pageable pageable) {
//	    return someService.search(pageable);
//	}
//	
	
	@RequestMapping("/home")
	public  long home(){
		System.out.println("restcontroller:: home: " + number);
		return repo.count();
	}
}