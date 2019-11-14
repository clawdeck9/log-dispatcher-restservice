package com.cluster9.logDispatcherRestService.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cluster9.logDispatcherRestService.dao.TagRepo;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.Tag;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.clustercld.logsmanager.entities.LogParagraph;


//l'idée:
//dans les threads créés par la lambda, il faut appeler des methodes qui permettent de gérer des transactions en toute indépendance
//je ne suis pas sur que ce soit le cas dans des appels par lambdas

@Service
public class LogService {

	@Autowired
	WebLogParagraphRepo logRepo;
	@Autowired
	TagRepo tagRepo;
	
	Logger logger = LoggerFactory.getLogger("com.cluster9.logDispatcherRestService.service");

	@Transactional
	public void populateDB(LogParagraph p) {
		
		logger.debug("wasCalled");
		
		WebLogParagraph newLog = new WebLogParagraph(p);
		Tag tag = tagRepo.findByName(newLog.getTag());
		if (tag != null) {
			System.out.println("tag exist?: " + tag.getName());
			tag.setComment("comment ok");
			tag.addWebLogParagraph(newLog);
			logRepo.save(newLog);
			newLog.setTagEntity(tag);
			// tagRepo.save(tag);
			logRepo.save(newLog);
		} else {
			tag = new Tag(newLog.getTag());
			tag.setComment("comment ok");
			tag.addWebLogParagraph(newLog);
			newLog.setTagEntity(tag);
			logRepo.save(newLog);
		}
	}
}
