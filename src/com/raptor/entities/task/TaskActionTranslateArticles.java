package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raptor.entities.core.Article;
import com.raptor.properties.Constants;

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
		
		for(Article article : articles){
			Article translated_article = new Article();
			translated_article = article;
			translated_article.setTitle(""+super.execute(article.getTitle()));
			translated_article.setContent(""+super.execute(article.getContent()));
			translated_articles.add(translated_article);
		}
		this.original_content=filled;
		this.needed_content=translated_articles;
		return translated_articles;
	}

	@Override
	public String getOriginalContentType() {
		return Constants.INTERNAL_CODE_TYPE_LIST_ARTICLE;
	}
}