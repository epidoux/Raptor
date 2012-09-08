package com.raptor.entities.tag;

import com.raptor.entities.task.Task;
import com.raptor.factories.TagFactory;


/**
 * This class represents a  balise (html or xml) : mother balise 
 * @author erik
 *
 */
public class Tag {
	
	protected Long id;
	
	protected String type;
			
	/**
	 * name
	 */
	protected String name;
	
	/**
	 * Attribut identifiant (partial if needed or regex)
	 */
	protected String identifiant;
		
	/**
	 * Attribut class
	 */
	protected String classe;
	
	/**
	 * Task link to a balise
	 */
	protected Task task;
	
	/**
	 * Constructeur
	 */
	public Tag(){

		this.setType(TagFactory.TYPE_MOTHER);
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the identifiant
	 */
	public String getIdentifiant() {
		return identifiant;
	}

	/**
	 * @param identifiant the identifiant to set
	 */
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * @return the classe
	 */
	public String getClasse() {
		return classe;
	}

	/**
	 * @param classe the classe to set
	 */
	public void setClasse(String classe) {
		this.classe = classe;
	}

	/**
	 * @return the task
	 */
	public Task getTask() {
		return task;
	}

	/**
	 * @param task the task to set
	 */
	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public String toString() {
		return "Tag [type=" + type + ", name=" + name + ", identifiant="
				+ identifiant + ", classe=" + classe + "]";
	}	
	
	
	
}
