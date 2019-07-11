package com.cluster9.logDispatcherRestService.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;


// create a one-to-many relation with the log entity
@SuppressWarnings("serial")
@Entity
public class Tag implements Serializable{
	
	@Id @GeneratedValue
	Long id;

	@NotBlank
	@Column(unique=true)
	String name;
	
	String comment;
	

	@OneToMany(mappedBy = "tag", cascade = CascadeType.PERSIST)
	private Set<WebLogParagraph> logs;//// only the set interface available
	
	//	for testing purposes only. It's not saved in the DB though. this is ok for primitives only
	@ElementCollection 
	@CollectionTable(name="log_set",joinColumns=@JoinColumn(name="tag_id"))
	@Column(name="log_names")
	private List<String> logNames; 
	
	public Tag() {
		super();
		this.logs = new HashSet<>();
	}
	// load WebLogParagraf from log_XXX.txt files folder
	public Tag(String tagName){
		this.name = tagName;
		this.logs = new HashSet<>();
		this.logNames = new ArrayList<String>();
		this.logNames.add("log first");
		this.logNames.add("log second");
	}


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the logNames
	 */
	public List<String> getLogNames() {
		return logNames;
	}
	/**
	 * @param logNames the logNames to set
	 */
	public void setLogNames(List<String> logNames) {
		this.logNames = logNames;
	}
	public void addWebLogParagraph(WebLogParagraph log) {
		System.out.println("addWebLogParagraph " + log);
		logs.add(log);
		log.setTagEntity(this);
	}
	
	@Override
	public String toString() {
		return "Tag: "+this.name+"\n  comment: "+this.comment+"\n  temp_names: "+this.logNames+"\n  logs: "+this.logs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//
//	public void addWeblogparagraph(WebLogParagraph log) {
//		this.logs.add(log);
//	}


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
//	
//    @Override
//    public boolean equals(Object o) {
//        if (this == o)
//            return true;
//             
//        if (!(o instanceof PostComment))
//            return false;
//             
//        return
//            id != null &&
//           id.equals(((PostComment) o).getId());
//    }
//    @Override
//    public int hashCode() {
//        return 31;
//    }
//	

}
