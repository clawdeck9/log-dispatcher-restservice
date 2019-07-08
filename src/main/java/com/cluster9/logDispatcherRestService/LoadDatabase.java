package com.cluster9.logDispatcherRestService;

import java.util.List;


import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.clustercld.logsmanager.DispatchLogFilesContent;
import com.clustercld.logsmanager.entities.LogParagraph;

//TODO: mettre le repo en autowired?
@Configuration
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(WebLogParagraphRepo repo) {
		return args -> {
			DispatchLogFilesContent dispatcher = new DispatchLogFilesContent();
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/claude/SSD-Claude/new_fs/ORG_PERSO/logs/testLogs");
			List<LogParagraph> logs = dispatcher.getLogParagraphs("/home/home/claude/java_workspace/log-dispatcher-restservice_notes/testLogs");
			//// pour tester la création de log et tag
			logs.stream().forEach( p -> repo.save(new WebLogParagraph(p)));
			//WebLogParagraph log1 = new WebLogParagraph(0, "fileNamePourTest20181102", "my_tag", "titleForTesting");
			//repo.save(log1);
		};
	}
}
