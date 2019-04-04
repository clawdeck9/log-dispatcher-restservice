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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
@RequestMapping("pars")
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

	@GetMapping("/logs/tags/{tag}")
	public Page<WebLogParagraph>  logsByTag(@PathVariable String tag, @NotNull final Pageable pageable){
		System.out.println("restcontroller::logsByTags method - restcontroller number= " + number);
		return repo.logParagraphByTag(tag, pageable );
	}
	
	@GetMapping("/logs/{id}")
	public Page<WebLogParagraph>  logById(@PathVariable Long id, @NotNull final Pageable pageable){
		System.out.println("restcontroller::logsById method - restcontroller number= " + number);
		return repo.logParagraphById(id, pageable );
	}
	
	@GetMapping("/tags")
	public Page<String> tags(final Pageable pageable){
		return repo.tags(pageable);
	}
	
//	tester ici si l'id est déjà utilisé
//	en fait tout reste à faire, le mieux serait de ne pas changer le constructeur de logpar, de ne pas ajouter l'id puisqu'il est en auto. l'id n'a rien à voir avec le contenu donc
//	rien à voir avec le user, pourtant, il reste le seul moyen de retrouver le logpar à detruire avant réécriture. On accepte de changer l'id et de créer un nouveau log avec un autre id
//	je peux quand même tester dans le repo si je peux recreer un log avec un id existant
    @PostMapping("/logs")
    public ResponseEntity<?> createLog(@Valid @RequestBody WebLogParagraph log, BindingResult result){
    	System.out.println("restcontroller::createLog method - restcontroller number= " + number + "log tag: "+log.getTag());
    	
    	ResponseEntity<?> errorMap = errorService.mapErrors(result);
        
        if(errorMap!=null) 
        	return errorMap;

    	WebLogParagraph createdLog = repo.saveAndFlush(log);
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
        
    	WebLogParagraph createdLog = repo.saveAndFlush(log);
    	return new ResponseEntity<WebLogParagraph>(createdLog, HttpStatus.CREATED);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteLog(@PathVariable Long id, @NotNull final Pageable pageable){
    	repo.deleteById(id);
    	return new ResponseEntity<String>("deleted", HttpStatus.ACCEPTED);
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