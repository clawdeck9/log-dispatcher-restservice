package com.cluster9.logDispatcherRestService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cluster9.logDispatcherRestService.dao.TagRepo;
import com.cluster9.logDispatcherRestService.entities.Tag;

@Service
public class TagService {
	
	@Autowired
	private TagRepo tagRepo;
	
//	public boolean existsByName(String tagName) {
//		if(tagRepo.findByName(tagName) != null) {
//			return true;
//		} else {
//			return false;
//		}
//	}

	public Page<Tag> findByName(String name, Pageable pageable) {
		
		  return tagRepo.findByName(name, pageable);
	}
	
	public Tag findByName(String name) {
		  return tagRepo.findByName(name);
	}


}
