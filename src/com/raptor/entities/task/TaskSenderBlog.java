package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents a task having something to send/publish on a blog in a scenario
 * @author erik
 * @version 1.0
 */
public abstract class TaskSenderBlog extends TaskSender implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * Blog user login 
    */
   protected String blogLogin;
   
   /**
    * Blog user pass 
    */
   protected String blogPass;
   
   /**
    * Blog email 
    */
   protected String blogEmail;
   
   /**
    * Blog link example with wordpress: (blogadress)/xml-rpc.php
    */
   protected String blogLink;
   
   
public String getBlogLogin() {
	return blogLogin;
}


public void setBlogLogin(String blogLogin) {
	this.blogLogin = blogLogin;
}


public String getBlogPass() {
	return blogPass;
}


public void setBlogPass(String blogPass) {
	this.blogPass = blogPass;
}


public String getBlogEmail() {
	return blogEmail;
}


public void setBlogEmail(String blogEmail) {
	this.blogEmail = blogEmail;
}



public String getBlogLink() {
	return blogLink;
}


public void setBlogLink(String blogLink) {
	this.blogLink = blogLink;
}


@Override
public String toString() {
	return "Blog "+super.toString();
}
   
}