package com.raptor.entities.task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.raptor.properties.Constants;
import com.raptor.properties.Log;
import com.raptor.services.core.EmailService;

/**
 * This class represents a task having something to send by email in a scenario
 * @author erik
 * @version 1.0
 * type = sender_email
 */
public class TaskSenderEmail extends TaskSender implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

   /**
    * List of emails which are recievers
    */
   protected List<String> emails;
   
   /**
    * Object of the email
    */
   protected String object;
   
   /**
    * Content of the email
    */
   protected String content;
   
   public List<String> getEmails(){
	   return emails;
   }
   
   public void setEmails(List<String> emails){
	   this.emails=emails;
   }
   
   public void addEmail(String email){
	   if(emails == null){
		   emails = new ArrayList<String>();
	   }
	   emails.add(email);
   }
   
   public void removeEmail(String email){
	   if(emails !=null){
		   emails.remove(email);
	   }
   }
   
   public String getObject(){
	   return object;
   }
   
   public void setObject(String obj){
	   object = obj;
   }
   
   public String getContent(){
	   return content;
   }
   
   public void setContent(String cont){
	   content = cont;
   }

   /**
    * Send a filled content to a list of emails
    * @param filled the content string
    * @return true or false if the job is done
    */
	@Override
	public Object execute(Object filled) throws Exception {
		String content = ""+filled;
		content = this.content.replace(Constants.INTERNAL_REGEX_CONTENT, ""+filled);
		
		//Trying to find the source
		if(this.addSource){
			String source = this.findSource();
			content = this.content.replace(Constants.INTERNAL_REGEX_SOURCE, source);
		}
		
		
		Boolean result = false;
		if(filled != null && filled != "" && content !="")result =EmailService.getInstance().sendMailSMTP(this.emails, this.object, content, false);
		else Log.getInstance().info("No need to send data because there is nothing");
		return result;
	}
	

	   @Override
	   public String toString() {
	   	return "Email "+super.toString();
	   }
}