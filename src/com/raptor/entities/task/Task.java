package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.raptor.entities.condition.Condition;
import com.raptor.entities.core.Scenario;
import com.raptor.factories.ConditionFactory;

/**
 * This class represents a task having something to do in a scenario
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
   
   public List<Condition<?>> getURLConditions(){
	   List<Condition<?>> conditions = new ArrayList<Condition<?>>();
	   for(Condition<?> cond : this.conditions){
		   if(cond.getPositionType().equals(ConditionFactory.POSITION_URL)){
			   conditions.add(cond);
		   }
	   }
	   
	   return conditions;
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

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	result = prime * result + ((scenario == null) ? 0 : scenario.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Task other = (Task) obj;
	if (id == null) {
		if (other.id != null)
			return false;
	} else if (!id.equals(other.id))
		return false;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	if (scenario == null) {
		if (other.scenario != null)
			return false;
	} else if (!scenario.equals(other.scenario))
		return false;
	return true;
}
   

   
}