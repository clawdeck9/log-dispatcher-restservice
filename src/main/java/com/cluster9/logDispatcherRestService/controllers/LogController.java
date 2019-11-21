package com.cluster9.logDispatcherRestService.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.ErrorBindingService;




//@RefreshScope
@RestController
@RequestMapping("/")
public class LogController {
	

    Logger logger = LoggerFactory.getLogger(LoggingController.class);
	@Autowired
	private WebLogParagraphRepo repo;
	@Autowired
	private ErrorBindingService errorService;
	
	int number = (int) (Math.random()*100);

	@GetMapping("/logs")
	public Iterable<WebLogParagraph>  logs(){
		return repo.findAll();
	}

	@GetMapping(value = "/logs-paged", params = { "tag"})
	public Page<WebLogParagraph>  logsByTag(@RequestParam("tag") String tag, @NotNull final Pageable pageable){
		return repo.findByTag(tag, pageable );
	}
	
	@GetMapping(value="/logs", params= {"tag"})
	public ResponseEntity<?>  logByIdPaged(@RequestParam String tag){
		
		List<WebLogParagraph> foundLogs = repo.findByTag(tag );
		return new ResponseEntity<List<WebLogParagraph>>(foundLogs, HttpStatus.OK);
	}
	
	@GetMapping(value="/log", params= {"id"})
	public ResponseEntity<?>  logById(@RequestParam Long id){

		Optional<WebLogParagraph> foundLog = repo.findById(id);
		if(foundLog.isPresent()) {
			return new ResponseEntity<WebLogParagraph>(foundLog.get(), HttpStatus.OK);
		} else {
			logger.debug("Optional<Log> {} was not found for the id {}", foundLog, id);
			return new ResponseEntity<WebLogParagraph>(new WebLogParagraph(0, "noLog", "", "empty"), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/logs")
    public ResponseEntity<?> createLog( @Valid @RequestBody WebLogParagraph log, BindingResult result){
    	System.out.println("restcontroller::createLog method - restcontroller number= " + number + "log tag: "+log.getTag());
    	
    	ResponseEntity<?> errorMap = errorService.mapErrors(result);
        
        if(errorMap!=null) 
        	return errorMap;

    	WebLogParagraph createdLog = repo.save(log);
    	return new ResponseEntity<WebLogParagraph>(createdLog, HttpStatus.CREATED);
    }
    
    // to update a log, an id must be sent as a param in the URL to delete the old log version and replace it
    @PutMapping("logs/{id}")
    public ResponseEntity<?> updateLog(@Valid @RequestBody WebLogParagraph log, @PathVariable Long id, BindingResult result){
    	System.out.println("restcontroller::updateLog method - restcontroller number= " + number + "log Title: "+log.getTitle());
    	
    	ResponseEntity<?> errorMap = errorService.mapErrors(result);
        if(errorMap!=null) 
        	return errorMap;

        if(id !=null) {
        	System.out.println("restcontroller::updateLog method; deleting log by id: " + id);
        	if(repo.existsById(id)){
            	repo.deleteById(id);
            }
        }
        
    	WebLogParagraph createdLog = repo.save(log);
    	return new ResponseEntity<WebLogParagraph>(createdLog, HttpStatus.CREATED);
    }
    
    @DeleteMapping
    public ResponseEntity<?> deleteLog(@PathVariable Long id, @NotNull final Pageable pageable){
    	repo.deleteById(id);
    	return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
    }
    

}






