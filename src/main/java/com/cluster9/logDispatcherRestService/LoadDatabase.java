package com.cluster9.logDispatcherRestService;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
//import com.cluster9.logDispatcherRestService.dao.TagRepo;
//import com.cluster9.logDispatcherRestService.dao.WebLogParagraphRepo;
//import com.cluster9.logDispatcherRestService.entities.Tag;
//import com.cluster9.logDispatcherRestService.entities.WebLogParagraph;
import com.cluster9.logDispatcherRestService.service.LogLoaderService;
import com.clustercld.logsmanager.DispatchLogFilesContent;
import com.clustercld.logsmanager.entities.LogParagraph;

// @Configuration
class LoadDatabase {

	@Autowired
	LogLoaderService logService;
	
	
	private String noStaticFunctionOkOrNo() {
		return "the instance level function works fine, thus you can read this!  ";
	}
	
	@Bean
	@Transactional
	CommandLineRunner initDatabase() {
		
		final int[] innerVar = {0,0,0,0};
				
		return args -> {
			DispatchLogFilesContent dispatcher = new DispatchLogFilesContent();
//			List<LogParagraph> logs = dispatcher.getLogParagraphs("/media/claude/SSD-Claude/new_fs/ORG_PERSO/logs/testLogs");
			List<LogParagraph> logs = dispatcher.getLogParagraphs("/home/home/claude/java_workspace/log-dispatcher-restservice_notes/testLogs");
			
			// System.out.println("\nLoadDatabase::tempTag.getLogs().toString(): "
			// +tempTag.getLogs().toString());
			
			// load the data to the base
			// this Function is here for learning purposes only
			DontChangeAnything<LogParagraph> dont = p -> {
				System.out.println(noStaticFunctionOkOrNo());
				System.out.println("map then forEach " + p.getTitle());
				return p;
			};
			
			logs.stream()
						.map(p -> dont.writeAComment(p))
						.map(p -> {
							innerVar[0] = (innerVar[0])+1;
							System.out.println(noStaticFunctionOkOrNo() + innerVar[0]);
							return p;
						})
						.forEach(p -> {
							logService.populateDB(p);
						});
		};
	}
}
