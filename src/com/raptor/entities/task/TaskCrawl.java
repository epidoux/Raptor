package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.raptor.entities.core.Article;


/**
 * This class represents a task having a crawl to do in a scenario
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