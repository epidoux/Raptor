package com.raptor.entities.core;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import com.raptor.entities.task.Task;
import com.raptor.properties.Log;
import com.raptor.services.core.RefilledService;

public class Scenario implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Id
    * Generated value
    */
   private Long id;

   /**
    * Name of a scenario
    */
   private String name;

   /**
    * Description of a scenario (filled by users)
    */
   private String description;

   /**
    * Date of creation
    */
   private Calendar dateCreation;

   /**
    * Date of the last execution
    */
   private Calendar dateLastExecution;

   /**
    * Number of minutes left between 2 executions
    */
   private Integer execMinutes;
   
   /**
    * List of tasks contained in the scenario
    */
   private List<Task> tasks;
   
   /**
    * User linked to this scenario
    */
   private User user;
   
   /**
    * Define is a scenario is activated
    */
   private Boolean active;
   
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public Calendar getDateCreation() {
      return this.dateCreation;
   }

   public void setDateCreation(Calendar date) {
      this.dateCreation = date;
   }

   public Calendar getDateLastExecution() {
      return this.dateLastExecution;
   }

   public void setDateLastExecution(Calendar date) {
      this.dateLastExecution = date;
   }
   
   public Integer getExecMinutes(){
	   return this.execMinutes;
   }
   
   public void setExecMinutes(Integer min){
	   execMinutes = min;
   }
   
   public List<Task> getTasks(){
	   return this.tasks;
   }
   
   public void setTasks(List<Task> tasks){
	   this.tasks = tasks;
   }

public Boolean getActive() {
	return active;
}

public void setActive(Boolean active) {
	this.active = active;
}

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}


public Boolean execute(){
	Boolean result = false;
	try{
		List<Task> tasks = this.getTasks();
		Object filled = null;
		String give = null;
		String need = null;
		Task pastTask = null;
		for(Task task : tasks){
			need = task.getInputContentType();
			give = task.getOutputContentType();
			Object data = null;
			if(pastTask!=null){
				if(filled!=null && need!=null){
					//We have to prepare the filled data into the expected content type
					data = RefilledService.getInstance().prepareData(pastTask.getOutputContentType(),need,filled);
				}
			}
			
			if(give!=null)filled = task.execute(data);	
			else task.execute(data);
			give = null;
			need = null;
			pastTask = task;
		}
		result = true;
		//Add the date execution time
		this.setDateLastExecution(Calendar.getInstance());
	}
	catch(Exception e){
		Log.getInstance().error("An error occured while executing the Scenario "+this, e);
	}
	return result;
}

@Override
public String toString() {
	return "Scenario [name=" + name + ", dateLastExecution="
			+ dateLastExecution + ", execMinutes=" + execMinutes + ", active="
			+ active + "]";
}
   
   
}