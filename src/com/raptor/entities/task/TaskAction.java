package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents an action task which modify something (translation, editing content...)
 * @author erik
 * @version 1.0
 */
public abstract class TaskAction extends Task implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;


   /**
    * Original content 
    * @var object (default string)
    */
   protected Object original_content;
   
   public abstract String getOriginalContentType();


	public Object getOriginal_content() {
		return original_content;
	}
	
	
	
	public void setOriginal_content(Object original_content) {
		this.original_content = original_content;
	}
   
   public abstract Object execute(Object filled) throws Exception ;
   
	@Override
	public String toString() {
		return "Action Task ";
	}
   
   
}