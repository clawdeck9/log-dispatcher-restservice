package com.cluster9.logDispatcherRestService;

import com.clustercld.logsmanager.entities.LogParagraph;

@FunctionalInterface
public interface DontChangeAnything {
	
	LogParagraph writeAComment(LogParagraph log);
	
	default LogParagraph writeADefaultComment(LogParagraph log) {
		System.out.println("functional interface default comment");
		return log;
	}

}
