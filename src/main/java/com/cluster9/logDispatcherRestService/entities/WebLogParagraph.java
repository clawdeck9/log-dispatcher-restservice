package com.cluster9.logDispatcherRestService.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.clustercld.logsmanager.entities.LogParagraph;
import com.fasterxml.jackson.annotation.JsonCreator;

@SuppressWarnings("serial")
@Entity
public class WebLogParagraph implements Serializable{

	@Id @GeneratedValue
	Long id;
	
	int index;
	String fileName;
	String tag = "noTag";
	String title  = "no title";
	ArrayList<String> lines;
	
	public WebLogParagraph() {
		super();
		lines = new ArrayList<>();
	}
	
	public WebLogParagraph(int index, String fileName, String tag, String title) {
		super();
		this.index = index;
		this.fileName = fileName;
		this.tag = tag;
		this.title = title;
	}

	public WebLogParagraph(LogParagraph p) {
		super();
		lines = new ArrayList<>();
		this.index = p.getIndex();
		this.fileName = p.getFileName();
		this.tag = p.getTag();
		this.title = p.getTitle();
		int len = 254;
		if (p.getLines().isEmpty()) {
			System.out.println("check this : no lines in paragraph");
		} else {
			p.getLines().stream().forEach(line -> {
				if (line != null) {
					if (line.length() < len)
						this.getLines().add(line);
					else
						System.out.println("WebLogParagraph::line truncated to:" + len);
				} else {
					System.out.println("check this : a null pointer instead of a line ");
				}
			});
		}
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public ArrayList<String> getLines() {
		return lines;
	}
	public void setLines(ArrayList<String> lines) {
		this.lines = lines;
	}
	
	public String addLine(String line) {
		this.lines.add(line);
		return line;
	}
	@Override
	public String toString() {
		return "WebLogParagraph [index=" + index + ", fileName=" + fileName + ", tag=" + tag + ", title=" + title
				+ ", lines=" + lines + "]";
	}
	
}
