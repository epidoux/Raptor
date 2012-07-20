package com.raptor.entities.task;

import java.io.Serializable;
import java.util.List;

import com.raptor.entities.core.Article;

import net.bican.wordpress.Page;
import net.bican.wordpress.Wordpress;


/**
 * This class represents a task having something to send/publish on a blog in a scenario
 * It use Wordpress API for JAVA developped by http://code.google.com/p/wordpress-java/
 * 
 * Input type: List<Article>
 * Output type : boolean
 * 
 * @author erik
 * @version 1.0
 */
public class TaskSenderBlogWordpress extends TaskSenderBlog implements Serializable {
 
	private static final long serialVersionUID = -3714824398624971951L;
	
	/**
	 * Execute the task and send the content to a blog site as a Post
	 * @param filled a list of Article
	 * @return true or false if it was executed
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Object execute(Object filled) throws Exception {
		List<Article> articles = (List<Article>) filled;
		Wordpress wp = new Wordpress(this.blogLogin, this.blogPass, this.blogLink);
		
		Boolean result = true;
		for(Article article : articles){
		    Page recentPost = wp.getRecentPosts(1).get(0);
		    recentPost.setTitle(article.getTitle());
		    recentPost.setDescription(article.getContent());
		    String post_id = wp.newPost(recentPost, false);
		    if((post_id==null || post_id.isEmpty()) && result) result=false;
		}
		
		return result;
	}
	
	
	@Override
	public String toString() {
		return "Wordpress "+super.toString();
	}
   
}