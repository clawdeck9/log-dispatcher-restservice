package com.cluster9.logDispatcherRestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cluster9.logDispatcherRestService.dao.TagRepo;
import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.Tag;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.clustercld.logsmanager.DispatchLogFilesContent;
import com.clustercld.logsmanager.entities.LogParagraph;

@Configuration
class LoadDatabase {
	@Autowired
	WebLogParagraphRepo logRepo;
	@Autowired
	TagRepo tagRepo;

	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			DispatchLogFilesContent dispatcher = new DispatchLogFilesContent();
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/claude/SSD-Claude/new_fs/ORG_PERSO/logs/testLogs");
			List<LogParagraph> logs = dispatcher.getLogParagraphs("/home/home/claude/java_workspace/log-dispatcher-restservice_notes/testLogs");
			
			//testing saveLog and tags
			Tag tempTag = new Tag("tester");
//			System.out.println("LoadDatabase::tag test: " + (tempTag.getName()));
//			
			WebLogParagraph tempLog = new WebLogParagraph(0, "claudelog20190407", "tester", "tester le save du tag");
			WebLogParagraph tempLog2 = new WebLogParagraph(0, "claudelog20190408", "tester", "tester le save du tag");
//			logRepo.save(tempLog2);

			tempTag.addWebLogParagraph(tempLog);
			tempTag.addWebLogParagraph(tempLog2);
			tempLog.setFileName("a brand new filename");
			tagRepo.save(tempTag);

//			tempLog.setTagEntity(tempTag);
//			tempLog2.setTagEntity(tempTag);
			
			logRepo.save(tempLog);
			logRepo.save(tempLog2);
			tempTag.setComment("there is a problem here");
			tagRepo.save(tempTag);

			System.out.println("LoadDatabase::tempTag.toString(): \n" +tempTag.toString());
			System.out.println("\nLoadDatabase::tempTag.getLogs().toString(): " +tempTag.getLogs().toString());
			// load the data to the base
//			logs.stream().forEach(p -> {
//				WebLogParagraph newLog = new WebLogParagraph(p);
//				Tag tag = tagRepo.tagByName(newLog.getTag());
//				if (tag != null) {
//					System.out.println("tag exist?: " + tag.getName());
//					tag.setComment("comment ok");
//					tag.addWeblogparagraph(newLog);
//					logRepo.save(newLog); // must save it twice to be in update mode for both the log and the tag
//					newLog.setTagEntity(tag);
//					// tagRepo.save(tag);
//					logRepo.save(newLog); // if this save the tag too, it's fine
//				} else {
//					tag = new Tag(newLog.getTag());
//					tag.setComment("comment ok");
//					tag.addWeblogparagraph(newLog);
//					newLog.setTagEntity(tag);
//					logRepo.save(newLog);
//				}
			// one log only, same tag than before
//			WebLogParagraph newLog = new WebLogParagraph(0, "claudelog20190407", "tester", "tester le save du tag");
//			Tag tag = tagRepo.tagByName(newLog.getTag());
//			if(tag != null) {
//				System.out.println("tag exist?: " + tag.getName());
//			} else {
//				tag = new Tag(newLog.getTag());
//			}
//			tag.setComment("comment ok");
//			logRepo.save(newLog);
//			newLog.setTagEntity(tag);
//			logRepo.save(newLog); // if this save the tag too, it's fine
//			
//			// one log only
//			WebLogParagraph newLog = new WebLogParagraph(0, "claudelog20190407", "tester2", "tester le save du tag");
//			Tag tag = new Tag(newLog.getTag());
//			tag.setComment("comment ok");
//			newLog.setTagEntity(tag);
//			logRepo.save(newLog); // if this save the tag too, it's fine

//			});
		};
	}
}
