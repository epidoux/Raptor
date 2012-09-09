package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.raptor.entities.core.Article;
import com.raptor.properties.Log;
import com.raptor.properties.RobotProperties;
import com.raptor.services.core.TranslatorService;

/**
 * This class represents a task which is used to translate a list of articles from a language to another
 * type = translate_articles
 * input = List<Article>
 * output = List<Article>
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