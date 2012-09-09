package com.raptor.entities.task;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.raptor.entities.core.Article;
import com.raptor.services.core.RssReaderService;

/**
 * This class represents a task having a crawl on a RSS stream to do in a scenario
 * type = crawl_html
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
public class TaskCrawlRss extends TaskCrawl implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   public TaskCrawlRss(){

		super();
		this.articles=new ArrayList<Article>();
   }
   /**
    * Execute a crawl task on a rss feed
    * @param nothing important
    * @return the articles
    */
   @Override
   public Object execute(Object filled) throws Exception {
	    NodeList nodes = RssReaderService.getInstance().parse(this.getLink());
	    Element element = null;
        for (int i = 0; i < nodes.getLength(); i++) {
            element = (Element) nodes.item(i);
            Article article = new Article();
            article.setTitle(RssReaderService.getInstance().readNode(element, "title"));
            article.setLink(RssReaderService.getInstance().readNode(element, "link"));
            article.setContent(RssReaderService.getInstance().readNode(element, "description"));
            SimpleDateFormat dfGMT = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH);
            dfGMT.parse(RssReaderService.getInstance().readNode(element, "pubDate"));
            article.setDateExecution(dfGMT.getCalendar());
            article.setTask(this);
        } 
		return this.getArticles();
   }


@Override
public String toString() {
	return "RSS "+super.toString();
}
   
}