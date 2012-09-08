package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raptor.entities.core.Article;
import com.raptor.properties.Constants;
import com.raptor.properties.Log;
import com.raptor.properties.RobotProperties;
import com.raptor.services.core.TranslatorService;

/**
 * This class represents a task which is used to translate a list of articles from a language to another
 * @author erik
 * @version 1.0
 * type = translate_articles
 * 
 * input = List<Article>
 * output = List<Article>
 */
public class TaskActionTranslateArticles extends TaskActionTranslate implements Serializable {
   
   private static final long serialVersionUID = 1L;
			
   @Override
   public String toString() {
   	return "Articles "+super.toString();
   }

   /**
    * Translate the content of a list of articles
    * @param filled the list of Article
    * @return the list translated
    */
	@SuppressWarnings("unchecked")
	@Override
	public Object execute(Object filled) throws Exception {
		List<Article> articles = (List<Article>) filled;
		List<Article> translated_articles = new ArrayList<Article>();
		//Check if the translator service is active
	  if(RobotProperties.getInstance().isServiceTranslatorActive()){
		for(Article article : articles){
			Article translated_article = new Article();
			translated_article = article;
			
			String cont = "";
			cont = TranslatorService.getInstance().translate(""+article.getTitle(), this.original_language, this.needed_language);
			if(this.getKeepOriginalContent())cont +="// "+article.getTitle();
			translated_article.setTitle(cont);

			cont = TranslatorService.getInstance().translate(""+article.getContent(), this.original_language, this.needed_language);
			if(this.getKeepOriginalContent())cont +="// "+article.getContent();
			
			translated_article.setContent(cont);
			translated_articles.add(translated_article);
		}
		this.needed_content=translated_articles;
	  }
	  else{
		  Log.getInstance().debug("[TranslatorService] This service is not active!", null);
		  translated_articles = articles;
	  }
	  
		return translated_articles;
	}

}