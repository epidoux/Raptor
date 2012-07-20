package com.raptor.services.orm;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.raptor.entities.condition.Condition;
import com.raptor.entities.core.Article;
import com.raptor.entities.core.Scenario;
import com.raptor.entities.core.User;
import com.raptor.entities.tag.Tag;
import com.raptor.entities.task.Task;


/**
 * Abstract mother class which represents a Connection to a file or bdd where datas are stored
 * @author erik
 * @version 1.0
 *
 */
public abstract class Connection{

	/**
	 * US date Format
	 */
	protected static DateFormat DF_US = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * Close a Connection
	 * @return true if succeed, or false
	 */
	public abstract Boolean close();
			
	  /**
     * Get all active users
     * @return the list of users
     * @throws Exception
     */
	public abstract List<User> getActiveUsers() throws Exception;

	 /**
     * Get all active scenarios
     * @return the list of scenarios
     * @throws Exception
     */
	public abstract List<Scenario> getScenarioByUser(User user) throws Exception;

	/**
	 * Get the tasks linked to a scenario
	 * @param scenario the scenario
	 * @return the list of tasks
	 * @throws exception
	 */
	public abstract List<Task> getTasksByScenario(Scenario scenario)
			throws Exception;

	
	/**
	 * Get the condition to ignore something in a Task
	 * @param task the task
	 * @return the list of conditions 
	 * @throws Exception 
	 */
	public abstract List<Condition<?>> getConditions(Task task) throws Exception;
	
	/**
	 * Get tags for a task
	 * @param task the task
	 * @return the list of tags
	 */
	public abstract List<Tag> getTags(Task task) throws Exception;

	/**
	 * Get articles linked to the task
	 * @param task the current task
	 * @return list of articles
	 * @throws Exception 
	 */
	public abstract List<Article> getArticles(Task task) throws Exception;
	


	/**
	 * save a scenario
	 * @param scenario the scenario 
	 * @return true if it succeed, false otherwise
	 * @throws Exception 
	 */
	public abstract Boolean saveScenario(Scenario scenario) throws Exception;

	/**
	 * save a task
	 * @param task the task 
	 * @return true if it succeed, false otherwise
	 * @throws Exception 
	 */
	public abstract Boolean saveTask(Task task) throws Exception;
	
	/**
	 * save an article
	 * @param article the article 
	 * @return true if it succeed, false otherwise
	 * @throws Exception 
	 */
	public abstract Boolean saveArticle(Article article) throws Exception;
	
	/**
	 * Escape the content by adding '\' to special characters
	 * @param content the content to escape
	 * @return the escaped content
	 */
	public String escape(String content){
		String quote = "\'";
		String guillemet =  "\"";
		String replace_quote = "\\'";
		String replace_guillement = "\\\"";
		content = content.replace(quote, replace_quote);
		content = content.replace(guillemet, replace_guillement);
		
		return content;
	}
	/**
	 * Present the Connection module
	 * @return the string
	 */
	public String toString(){
		return "Connection module used to store datas";
	}
}
