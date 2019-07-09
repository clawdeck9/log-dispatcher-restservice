package com.cluster9.logDispatcherRestService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cluster9.logDispatcherRestService.dao.TagRepo;
import com.cluster9.logDispatcherRestService.entities.Tag;

@RestController
public class TagController {
	
	@Autowired TagRepo tagRepo;
	
	// pages of tags(should not be used for prod)
	@GetMapping(value="tags", params= {"name"})
	public Page<Tag> tagsByName(@RequestParam String name, Pageable pageable){
		return tagRepo.tagsByName(name, pageable);
	}
	
	// a unique tag (tag's names are unique)
	@GetMapping(value="tag", params= {"name"})
	public Tag tagByName(@RequestParam String name) {
		return tagRepo.tagByName(name);
	}
	
	// all tags
	@GetMapping("tags")
	public Page<Tag> findAll(Pageable pageable){
		Page<Tag> page = tagRepo.findAll(pageable);
		return page;
	}
}
