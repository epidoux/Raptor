package com.raptor.entities.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import com.raptor.entities.condition.Condition;
import com.raptor.entities.core.Article;
import com.raptor.entities.tag.Tag;
import com.raptor.properties.Log;
import com.raptor.services.core.HtmlExtractorService;

/**
 * This class represents a task having a crawl pn a website to do in a scenario
 * @author erik
 * @version 1.0
 * type = crawl_html
 */
public class TaskCrawlHtml extends TaskCrawl implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;
			
	/**
	 * Tags linked
	 */
	protected List<Tag> tags;
	
	
	/**
	 * Is there multi pages to crawl? if yes linked a condition
	 */
	protected Condition<?> condition;
	
	/**
	 * Constructeur
	 */
	public TaskCrawlHtml(){
		super();
		this.articles=new ArrayList<Article>();
		this.tags=new ArrayList<Tag>();
		this.conditions = new ArrayList<Condition<?>>();
	}

	/**
	 * @return the tags
	 */
	public List<Tag> getTags() {
		return tags;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public Object execute(Object filled) throws Exception {
		//Parse the website
		
		HttpURLConnection con = (HttpURLConnection) new URL(this.getLink()).openConnection();
		
		InputStream is = con.getInputStream();
		InputStreamReader ipsr=new InputStreamReader(is);
		BufferedReader br=new BufferedReader(ipsr);
		String line="";
		String content="";
		while ((line=br.readLine())!=null){
			content+=line+"\n";
		}
		List<Article> articles = HtmlExtractorService.getInstance().parse(this.getLink(), content,this);
		//Conditions on the content of an article
		List<Condition<?>> conditions =this.getConditions();
		int i = 0;
		List<Article> arts = this.getArticles();
		List<Integer> listToAdd = new ArrayList<Integer>();
		for(Article article : articles){
			//we check that a task doesn't contain the article yet
			Boolean refus = false;
			for(Article a : arts){
				if(article.getTitle().equals(a.getTitle()) && article.getContent().equals(a.getContent())){
						refus = true;
						Log.getInstance().debug("Refuse "+article+" because it's already in "+this, null);
						
				}
			}
			
			if(!refus){
				Boolean decline = false;
				for(Condition<?> condition : conditions){
					//test of conditions
					if(!decline) decline = condition.isExclude(article,condition);   
				}
				if(!decline){
					this.getArticles().add(article);
					listToAdd.add(i);
				}
				else{
					Log.getInstance().debug("Refuse "+article+" because of conditions on task ", null);
				}
			}
			
			i++;
		}
		//restruct a valid list of articles
		List<Article> validArticles = new ArrayList<Article>();
		for(int j : listToAdd){
			validArticles.add(this.getArticles().get(j));
		}
		this.setArticles(validArticles);
		
		Log.getInstance().info("Get "+this.getArticles().size()+" article(s)");
		
		return this.getArticles();
	}

	   @Override
	   public String toString() {
	   	return "HTML "+super.toString();
	   }
}