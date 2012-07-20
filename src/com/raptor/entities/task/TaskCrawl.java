package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.raptor.entities.core.Article;


/**
 * This class represents a task having a crawl to do in a scenario
 * @author erik
 * @version 1.0
 */
public abstract class TaskCrawl extends Task implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Articles which are results of the crawl
    */
   protected List<Article> articles;
   
	/**
	 * Link to crawl
	 */
	protected String link;
	

	
	public String getLink(){
		return this.link;
	}
	
	public void setLink(String link){
		this.link = link;
	}
   
   public List<Article> getArticles(){
	   return this.articles;
   }
   
   public void setArticles(List<Article> art){
	   this.articles = art;
   }
   
   public void addArticle(Article art){
	   if(this.articles == null){
		   articles = new ArrayList<Article>();
	   }
	   articles.add(art);
   }
   
   //TODO à vérifier le remove
   public void removeArticle(Article art){
	   if(articles.contains(art)){
		   articles.remove(art);
	   }
   }
   
   @Override
   public String toString() {
   	return "Crawl Task "+super.toString();
   }
   
}