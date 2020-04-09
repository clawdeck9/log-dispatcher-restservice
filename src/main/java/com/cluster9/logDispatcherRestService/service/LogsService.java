package com.cluster9.logDispatcherRestService.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;

@Service
public class LogsService {
	
	@Autowired
	WebLogParagraphRepo logRepo;
	// Title list by tag
	public List<String> findTitlesByTag(String tag){
		return logRepo.findByTag(tag).stream().map( p -> p.getTitle()).collect(Collectors.toList());
	}

}
