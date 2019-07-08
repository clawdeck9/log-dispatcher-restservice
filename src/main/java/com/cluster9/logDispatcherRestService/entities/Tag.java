package com.cluster9.logDispatcherRestService.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;




// create a one-to-many relation with the log entity
@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable{
	
	@Id @GeneratedValue
	Long id;
	String name;
	String comment;
	@OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
	private Set<WebLogParagraph> logs;
	
	
	// load WebLogParagraf from log_XXX.txt files folder
	Tag(String tagName){
		this.name = tagName;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void addWeblogparagraph(WebLogParagraph log) {
		this.logs.add(log);
	}


	/**
	 * @return the logs
	 */
	public Set<WebLogParagraph> getLogs() {
		return logs;
	}


	/**
	 * @param logs the logs to set
	 */
	public void setLogs(Set<WebLogParagraph> logs) {
		this.logs = logs;
	}


	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}


	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	

}
