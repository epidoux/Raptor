package com.raptor.entities.task;

import java.io.Serializable;

import com.raptor.entities.core.Scenario;

/**
 * This class represents a task having something to send in a scenario
 * @author Eric Pidoux
 * @version 1.0
 *  This file is part of Raptor.
 *  Raptor is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Raptor is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Raptor.  If not, see <http://www.gnu.org/licenses/>
 *
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