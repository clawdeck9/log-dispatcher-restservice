package com.cluster9.logDispatcherRestService.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.ErrorBindingService;




//@RefreshScope
@RestController
@RequestMapping("cld")
public class LogRestController {
	
	
	@Autowired
	private WebLogParagraphRepo repo;
	@Autowired
	private ErrorBindingService errorService;
	
	int number = (int) (Math.random()*100);

	@GetMapping("/logs")
	public List<WebLogParagraph>  logs(){
		System.out.println("restcontroller::logs method - restcontroller number= " + number);
		return repo.findAll();
	}

	@GetMapping("/logs/{tag}")
	public Page<WebLogParagraph>  logsByTag(@PathVariable String tag, @NotNull final Pageable pageable){
		System.out.println("restcontroller::logsByTags method - restcontroller number= " + number);
		return repo.logParagraphByTag(tag, pageable );
	}
	
    @PostMapping("/logs")
    public ResponseEntity<?> createLog(@Valid @RequestBody WebLogParagraph log, BindingResult result){
    	System.out.println("restcontroller::createLog method - restcontroller number= " + number + "log tag: "+log.getTag());
    	System.out.println("restcontroller::createLog method - restcontroller number= " + number + "log calendar type: "+ log.getCreatedDate());
        ResponseEntity<?> errorMap = errorService.mapErrors(result);
        
        if(errorMap!=null) 
        	return errorMap;
        
    	WebLogParagraph createdLog = repo.saveAndFlush(log);
    	return new ResponseEntity<WebLogParagraph>(createdLog, HttpStatus.CREATED);
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