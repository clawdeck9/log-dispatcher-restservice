package com.cluster9.logDispatcherRestService.entities;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class LogParagraph implements Serializable{
	
	@Id @GeneratedValue
	Long id;
	
	int index;
	String fileName;
	String tag = "noTag";
	String title  = "no title";
	ArrayList<String> lines;
	
	public LogParagraph() {
		super();
		lines = new ArrayList<>();
	}
	
	public LogParagraph(int index, String fileName, String tag, String title) {
		super();
		this.index = index;
		this.fileName = fileName;
		this.tag = tag;
		this.title = title;
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
		return "LogParagraph [index=" + index + ", fileName=" + fileName + ", tag=" + tag + ", title=" + title
				+ ", lines=" + lines + "]";
	}
	
}
