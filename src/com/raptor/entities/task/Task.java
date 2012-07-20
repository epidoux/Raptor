package com.raptor.entities.task;

import java.io.Serializable;
import java.util.List;
import com.raptor.entities.condition.Condition;
import com.raptor.entities.core.Scenario;

/**
 * This class represents a task having something to do in a scenario
 * @author erik
 * @version 1.0
 */
public abstract class Task implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Id
    * Generated value
    */
   protected Long id;

   /**
    * Name of the task
    */
   protected String name;

   /**
    * Description of the task
    */
   protected String description;
   
   /**
    * The position to execute the task in the scenario
    */
   protected Integer position;
   
   /**
    * Conditions to not execute the task
    */
   protected List<Condition<?>> conditions;
   
   /**
    * Input content type for the execution
    */
   protected String inputContentType;
   
   /**
    * Output content type for the execution
    */
   protected String outputContentType;

   /**
    * Type of task
    */
   protected String type;
   
   /**
    * The scenario linked
    */
   protected Scenario scenario;

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

   public String getType() {
      return this.type;
   }

   public void setType(String type) {
      this.type = type;
   }
   
   public List<Condition<?>> getConditions(){
	   return this.conditions;
   }
   
   public void setConditions(List<Condition<?>> conditions){
	   this.conditions=conditions;
   }

public Integer getPosition() {
	return position;
}

public void setPosition(Integer position) {
	this.position = position;
}

public Scenario getScenario() {
	return scenario;
}

public void setScenario(Scenario scenario) {
	this.scenario = scenario;
}



public String getInputContentType() {
	return inputContentType;
}

public void setInputContentType(String inputContentType) {
	this.inputContentType = inputContentType;
}

public String getOutputContentType() {
	return outputContentType;
}

public void setOutputContentType(String outputContentType) {
	this.outputContentType = outputContentType;
}

/**
 * Function called to execute a task 
 * @param filled some data needed
 * @return data (results of the task)
 */
public abstract Object execute(Object filled) throws Exception ;

@Override
public String toString() {
	return "[id=" + id + ", name=" + name + ", inputContentType="
			+ inputContentType + ", outputContentType=" + outputContentType
			+ ", type=" + type + "]";
}
   
   
}