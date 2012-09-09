package com.raptor.entities.condition;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.raptor.entities.core.Article;
import com.raptor.entities.task.Task;
import com.raptor.factories.ConditionFactory;

/**
 * Define a denied condition
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
public abstract class Condition<T> {
	
	protected Long id;
	
	protected String type;
	
	/**
	 * Value of the condition
	 */
	protected T value;
	
	/**
	 * Define the position of the condition(url or content)
	 */
	protected String positionType;
		
	/**
	 * Regex of the position
	 */
	protected String positionRegex;
	
	/**
	 * Signe of condition
	 * >, < , = , !=
	 */
	protected String signe;
	
	/**
	 * Task linked
	 */
	protected Task task;

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
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * @return the position
	 */
	public String getPositionType() {
		return positionType;
	}

	/**
	 * @param position the position to set
	 */
	public void setPositionType(String position) {
		this.positionType = position;
	}
	
	/**
	 * @return the positionRegex
	 */
	public String getPositionRegex() {
		return positionRegex;
	}

	/**
	 * @param positionRegex the positionRegex to set
	 */
	public void setPositionRegex(String positionRegex) {
		this.positionRegex = positionRegex;
	}

	/**
	 * @return the signe
	 */
	public String getSigne() {
		return signe;
	}

	/**
	 * @param signe the signe to set
	 */
	public void setSigne(String signe) {
		this.signe = signe;
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
		
	
	/**
	 * Evaluate the condition with the sign
	 * @param article the article
	 * @param condition the condition linked
	 * @return true if it had to be excluded, false otherwise
	 */
	public abstract Boolean isExclude(Article article, Condition<?> condition);
	
	
	/**
	 * Evaluate a regex with a value, signe and condition
	 * @param regex the regex
	 * @param valeur the value to test
	 * @param contenu the content
	 * @param condition the condition
	 * @return the result
	 */
	protected Boolean evaluate(String regex, String valeur, String contenu,Condition<?> condition){
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(contenu);
		Boolean resultat=false;
		while (matcher.find()) {
			try{
				String resu =matcher.group();
				if(ConditionFactory.getInstance().isSigneDifferent(condition.getSigne())){
					resultat =resu!=valeur;
				}
				else if(ConditionFactory.getInstance().isSigneEgal(condition.getSigne())){
					resultat =resu==valeur;
				}
				else if(ConditionFactory.getInstance().isSigneInferieur(condition.getSigne())){
					resultat =Integer.parseInt(resu)<Integer.parseInt(valeur);
				}
				else if(ConditionFactory.getInstance().isSigneInferieurEgal(condition.getSigne())){
					resultat =Integer.parseInt(resu)<=Integer.parseInt(valeur);
				}
				else if(ConditionFactory.getInstance().isSigneSuperieur(condition.getSigne())){
					resultat =Integer.parseInt(resu)>=Integer.parseInt(valeur);
				}
				else if(ConditionFactory.getInstance().isSigneSuperieurEgal(condition.getSigne())){
					resultat =Integer.parseInt(resu)>=Integer.parseInt(valeur);
				}
			}
			catch(Exception e){
				resultat=false;
				
			}
		}
		return resultat;
	}
	
	/**
	 * Test if a link is excluded or not
	 * @param url the string url (complete!)
	 * @return true or false
	 */
	public Boolean isExcludedUrl(String url){
		Article article = new Article();
		article.setLink(url);
		return this.isExclude(article, this);
	}

	@Override
	public String toString() {
		return "Condition [id=" + id + ", type=" + type + ", value=" + value
				+ ", positionType=" + positionType + ", positionRegex="
				+ positionRegex + ", signe=" + signe + "]";
	}
	
	
}
