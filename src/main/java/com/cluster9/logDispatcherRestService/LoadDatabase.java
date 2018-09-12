package com.cluster9.logDispatcherRestService;

//import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cluster9.logDispatcherRestService.dao.LogParagraphRepo;
import com.cluster9.logDispatcherRestService.entities.LogParagraph;

@Configuration
//@Slf4j
class LoadDatabase {

	@Bean
	CommandLineRunner initDatabase(LogParagraphRepo repo) {
		
		return args -> {
			ArrayList<String> list =  new ArrayList<String>();
			list.add("one  sentence"); 
			list.add("another sentence");
			list.add("a third sentence");
			list.add("and the final one");
			Stream.of("java", "ccp", "js", "angular")
					.forEach(title ->  repo.save(new LogParagraph(0, "log_filexxx", "test", title)));
			// repo.findAll().forEach(p -> p.setLines(list));
		};
	}
}
