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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((link == null) ? 0 : link.hashCode());
		result = prime * result + ((task == null) ? 0 : task.hashCode());
		return result;
	}

	/**
	 * Test if an article is equals to another one 
	 * on the title, link and content field
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (link == null) {
			if (other.link != null)
				return false;
		} else if (!link.equals(other.link))
			return false;
		if (task == null) {
			if (other.task != null)
				return false;
		} else if (!task.equals(other.task))
			return false;
		return true;
	}

	
	
	
	
}
