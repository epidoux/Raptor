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
    * Add source link to the content send (use regex if its email)
    */
   protected Boolean addSource;


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




@Override
   public String toString() {
   	return "Sender Task "+super.toString();
   }
   
   
}