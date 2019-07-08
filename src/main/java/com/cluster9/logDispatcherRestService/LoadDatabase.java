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
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/claude/SSD-Claude/new_fs/ORG_PERSO/logs/testLogs");
			List<LogParagraph> logs = dispatcher.getLogParagraphs("/home/home/claude/java_workspace/log-dispatcher-restservice_notes/testLogs");
			logs.stream().forEach( p -> repo.save(new WebLogParagraph(p)));
		};
	}
}
