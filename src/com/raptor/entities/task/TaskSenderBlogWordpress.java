package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;

import com.raptor.entities.core.Article;
import com.raptor.properties.Log;



/**
 * This class represents a task having something to send/publish on a blog in a scenario
 * It use Wordpress Post publish by email http://en.support.wordpress.com/post-by-email/
 * 
 * Input type: List<Article>
 * Output type : boolean
 * 
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
public class TaskSenderBlogWordpress extends TaskSenderBlog implements Serializable {
 
	private static final long serialVersionUID = -3714824398624971951L;
	
	private static final String NOTIFICATION_PAGE = "wp-mail.php";
	
	/**
	 * Execute the task and send the content to a blog site as a Post
	 * @param filled a list of Article
	 * @return true or false if it was executed
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object execute(Object filled) throws Exception {
		List<Article> articles = (List<Article>) filled;
		
		Log.getInstance().debug("Connect to wordpress blog "+this.blogLink, null);
		Boolean result = true;
		List<String> emails = new ArrayList<String>();
		emails.add(this.blogEmail);
		for(Article article : articles){

			
			//check if we have to add the source
			//if(this.getAddSource()) content += " Source : "+this.blogLink;
			
			//EmailService.getInstance().sendMailSMTP(emails, article.getTitle(), content, false);
			Wordpress wp = new Wordpress("admin", "N)dQuZl**)H7", this.blogLink+"/xmlrpc.php");
		    Page post = new Page();
		    post.setDescription(StringEscapeUtils.escapeHtml(article.getContent()));
		    post.setTitle(StringEscapeUtils.escapeHtml(article.getTitle()));
		    wp.newPost(post,false);
		}
		
		
	    if(result)Log.getInstance().info("All the articles was sent to wordpress");
	
		return result;
	}
	
	
	@Override
	public String toString() {
		return "Wordpress "+super.toString();
	}
   
}