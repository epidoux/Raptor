package com.raptor.entities.task;

import java.io.Serializable;

import com.raptor.entities.core.Article;
import com.raptor.entities.core.Scenario;
import com.raptor.properties.Constants;

/**
 * This class represents a task having something to send in a scenario
 * @author erik
 * @version 1.0
 */
public abstract class TaskSender extends Task implements Serializable {
	
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Keep the original content of the task given if it was edited (translation,...)
    */
   protected Boolean keepOriginalContent;
   
   /**
    * Original content of the task given if it's asked
    * @var boolean
    */
   protected String originalContent;
   
   /**
    * Add source link to the content send (use regex if its email)
    */
   protected Boolean addSource;

   
   
   public Boolean getKeepOriginalContent() {
	return keepOriginalContent;
}



public void setKeepOriginalContent(Boolean keepOriginalContent) {
	this.keepOriginalContent = keepOriginalContent;
}



public String getOriginalContent() {
	return originalContent;
}



public void setOriginalContent(String originalContent) {
	this.originalContent = originalContent;
}



public Boolean getAddSource() {
	return addSource;
}



public void setAddSource(Boolean addSource) {
	this.addSource = addSource;
}


/* OPS FUNCTIONS */

/**
 * Find the source of the task Crawl if exists
 * @return the source or nothing
 */
protected String findSource(){
	Scenario s = this.getScenario();
	String source = "";
	for(Task task : s.getTasks()){
		if(task instanceof TaskCrawl){
			//ok find the source now
			source = ((TaskCrawl) task).getLink();
		}
	}
	return source;
}

/**
 * Find the original content of the task 
 * @return the original content of the task
 */
protected String findOriginalContent(){
	Scenario s = this.getScenario();
	String result = "";
	for(Task task : s.getTasks()){
		if(task instanceof TaskAction){
			//ok find the original content
			if(((TaskAction)task).getOriginalContentType().equals(Constants.INTERNAL_CODE_TYPE_STRING)){
				result = (String) ((TaskAction)task).getOriginal_content();
			}
			else if(Constants.INTERNAL_CODE_TYPE_ARTICLE.equals(((TaskAction)task).getOriginal_content())){
				Article a=(Article) ((TaskAction)task).getOriginal_content();
				result = a.getContent();
			}
		}
	}
	return result;
}


@Override
   public String toString() {
   	return "Sender Task "+super.toString();
   }
   
   
}