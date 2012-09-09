package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents a task having something to send/publish on a blog in a scenario
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