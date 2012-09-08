package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;

import com.raptor.entities.core.Article;
import com.raptor.properties.Log;
import com.raptor.services.core.EmailService;
import com.raptor.services.core.HtmlExtractorService;



/**
 * This class represents a task having something to send/publish on a blog in a scenario
 * It use Wordpress Post publish by email http://en.support.wordpress.com/post-by-email/
 * 
 * Input type: List<Article>
 * Output type : boolean
 * 
 * @author erik
 * @version 1.0
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