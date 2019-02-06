package com.cluster9.logDispatcherRestService;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.clustercld.logsmanager.DispatchLogFilesContent;
import com.clustercld.logsmanager.entities.LogParagraph;

@Configuration
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(WebLogParagraphRepo repo) {
		return args -> {
			DispatchLogFilesContent dispatcher = new DispatchLogFilesContent();
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/home/toshubu/Documents/logs4testing");
			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/toshubu/SYSTEM/Users/claude/Documents/ORG_PERSO/logs/logs");
			logs.stream().forEach( p -> repo.save(new WebLogParagraph(p)));
		};
	}
}
