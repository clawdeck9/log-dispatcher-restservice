package com.cluster9.logDispatcherRestService.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
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

	@RequestMapping("/logpars")
	public List<WebLogParagraph>  logpars(){
		System.out.println("restcontroller::logpars path: " + number);
		return repo.findAll();
	}
	@RequestMapping("/home")
	public  long home(){
		System.out.println("restcontroller::logpars home: " + number);
		return repo.count();
	}
}