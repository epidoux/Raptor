package com.raptor.factories;

import com.raptor.entities.task.Task;
import com.raptor.entities.task.TaskCrawlHtml;
import com.raptor.entities.task.TaskCrawlRss;
import com.raptor.entities.task.TaskSenderBlogWordpress;
import com.raptor.entities.task.TaskSenderEmail;
import com.raptor.entities.task.TaskActionTranslate;
import com.raptor.entities.task.TaskActionTranslateArticles;

/**
 * Factory of task
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
public class TaskFactory {

	/**
	 * Instance of the factory
	 */
	private static TaskFactory INSTANCE;
	

	/**
	 * Key task crawler html
	 */
	public static final String TASK_CRAWLER_HTML="CRAWL_HTML";
	
	/**
	 * Key task crawler rss
	 */
	public static final String TASK_CRAWLER_RSS="CRAWL_RSS";

	/**
	 * Key task sender email
	 */
	public static final String TASK_SENDER_EMAIL="SENDER_EMAIL";

	/**
	 * Key task sender blog wordpress
	 */
	public static final String TASK_SENDER_BLOG_WORDPRESS="SENDER_BLOG_WORDPRESS";
	
	/**
	 * Key task translate articles
	 */
	public static final String TASK_TRANSLATE_ARTICLE = "TRANSLATE_ARTICLES";

	/**
	 * Key task translate string
	 */
	public static final String TASK_TRANSLATE = "TRANSLATE";
	
	
	/**
	 * Constructeur privé
	 */
	private TaskFactory(){
		
	}
	
	/**
	 * Récupération de l'instance de l'usine à entité
	 * @return l'instance correspondante
	 */
	public static TaskFactory getInstance(){
		if(INSTANCE==null){
			INSTANCE=new TaskFactory();
			
		}
		return INSTANCE;
	}
	
	/**
	 * Create an instance of Task
	 * @param key the key given 
	 * @return the instance 
	 */
	public Task create(String code){
		Task b = null;
		if(TASK_CRAWLER_HTML.equals(code)){
			b= new TaskCrawlHtml();
			b.setType(TASK_CRAWLER_HTML);
		}
		else if(TASK_CRAWLER_RSS.equals(code)){
			b = new TaskCrawlRss();
			b.setType(TASK_CRAWLER_RSS);
		}
		else if(TASK_SENDER_EMAIL.equals(code)){
			b = new TaskSenderEmail();
			b.setType(TASK_SENDER_EMAIL);
		}
		else if(TASK_TRANSLATE.equals(code)){
			b = new TaskActionTranslate();
			b.setType(TASK_TRANSLATE);
		}
		else if(TASK_TRANSLATE_ARTICLE.equals(code)){
			b = new TaskActionTranslateArticles();
			b.setType(TASK_TRANSLATE_ARTICLE);
		}
		else if(TASK_SENDER_BLOG_WORDPRESS.equals(code)){
			b = new TaskSenderBlogWordpress();
			b.setType(TASK_SENDER_BLOG_WORDPRESS);
		}
		return b;
	}
	
	/**
	 * Return the task which corresponds to the key given
	 * @param key the key 
	 * @return the task needed
	 */
	public Task find(String key){
		Task b =this.create(key);
		return b;
	}
	
}
