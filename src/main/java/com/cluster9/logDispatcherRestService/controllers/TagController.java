package com.cluster9.logDispatcherRestService.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cluster9.logDispatcherRestService.dao.TagRepo;
import com.cluster9.logDispatcherRestService.entities.Tag;
@RestController
@RequestMapping("tags")
public class TagController {
	
//	@Autowired
//	private TagRepo tr;
//	
//	@GetMapping(params={"tagName"})
//	public ResponseEntity<Tag> getTagByName(@RequestParam String tagName) {
//		Tag tag =  tr.findByName(tagName);
//		return new ResponseEntity<Tag>(tag, HttpStatus.ACCEPTED);
//	}
//	@GetMapping(params={"tagName"})
//	public Page<Tag> getTagByName(@RequestParam String tagName, Pageable page) {
//		return tr.findByName(tagName);
//	}

}
