package com.cluster9.logDispatcherRestService.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.clustercld.logsmanager.entities.LogParagraph;

@SuppressWarnings("serial")
@Entity
public class WebLogParagraph implements Serializable {

	@Id
	@GeneratedValue
	Long id;

	int index;
//	@NotBlank( message="Validation: There must be a file name! ")
    @NotBlank(message ="The file name is required")
    String fileName;
    @NotBlank(message ="A tag is required")
    @Size(min=2, max=15, message = "Please use less than 15 characters and no spaces")
    String tag;
    @NotBlank(message ="A title is required")
    String title = "no title";
	@Lob
	@Column(name = "full_line", length = 48000)
	String lines;

	public WebLogParagraph() {
		super();
		this.lines = "";
	}

	public WebLogParagraph(int index, String fileName, String tag, String title) {
		super();
		this.index = index;
		if(fileName == null)
			fileName = "no file name";
		this.fileName = fileName;
		this.tag = tag;
		this.lines = "";
		this.title = title;
	}

	public WebLogParagraph(LogParagraph p) {
		super();
		this.index = p.getIndex();
		if(p.getFileName() == null)
			p.setFileName("no file name");
		else
			this.fileName = p.getFileName();
		this.tag = p.getTag();
		this.title = p.getTitle();
		this.lines = "";
		this.castLines(p);
	}

	private void castLines(LogParagraph p) {
		if (p.getLines().isEmpty()) {
			System.out.println("check this : no lines in paragraph");
		} else {
			p.getLines().stream().forEachOrdered( line -> {
				if (!(line.isEmpty())) {
					this.lines = this.lines.concat(line);
					// System.out.println("WebLogParagraph" + this.title + "line written " + this.getLines().length()	+ "  line:" + line.length());
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

	public String getLines() {
		return lines;
	}

	public void setLines(String lines) {
		this.lines = lines;
	}

	public String addLine(String line) {
		this.lines = this.lines.concat(line);
		return line;
	}

	@Override
	public String toString() {
		return "WebLogParagraph [index=" + index + ", fileName=" + fileName + ", tag=" + tag + ", title=" + title
				+ ", lines=" + lines + "]";
	}

}
