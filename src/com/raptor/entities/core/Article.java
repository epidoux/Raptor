package com.raptor.entities.core;

import java.io.Serializable;
import java.util.Calendar;
import com.raptor.entities.task.Task;


public class Article implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
    private String title; 
	
	private String content; 
	
	private String link; 
	
	private Calendar dateExecution; 
	
	private Task task;
	
	
	public Article() {
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



	public String getTitle() {
 		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
 		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getLink() {
 		return this.link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	   
	public Calendar getDateExecution() {
 		return this.dateExecution;
	}

	public void setDateExecution(Calendar dateExecution) {
		this.dateExecution = dateExecution;
	}
	   
	public Task getTask() {
 		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + "]";
	}
	
	
}
