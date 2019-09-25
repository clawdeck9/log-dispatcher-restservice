package com.cluster9.logDispatcherRestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

//import com.cluster9.logDispatcherRestService.dao.TagRepo;
//import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
//import com.cluster9.logDispatcherRestService.entities.Tag;
//import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.LogService;
import com.clustercld.logsmanager.DispatchLogFilesContent;
import com.clustercld.logsmanager.entities.LogParagraph;

@Configuration
class LoadDatabase {

	@Autowired
	LogService logService;

	@Bean
	@Transactional
	CommandLineRunner initDatabase() {
		return args -> {
			DispatchLogFilesContent dispatcher = new DispatchLogFilesContent();
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/claude/SSD-Claude/new_fs/ORG_PERSO/logs/testLogs");
			List<LogParagraph> logs = dispatcher
					.getLogParagraphs("/home/home/claude/java_workspace/log-dispatcher-restservice_notes/testLogs");
			// System.out.println("\nLoadDatabase::tempTag.getLogs().toString(): "
			// +tempTag.getLogs().toString());
			// load the data to the base
			logs.stream().forEach(p -> {
				logService.populateDB(p);
			});
		};
	}
}
