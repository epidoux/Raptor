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
    * Keep original content
    */
   private Boolean keepOriginalContent;
   
   
   
   public Boolean getKeepOriginalContent() {
	return keepOriginalContent;
   }

	public void setKeepOriginalContent(Boolean keepOriginalContent) {
		this.keepOriginalContent = keepOriginalContent;
	}

	public abstract Object execute(Object filled) throws Exception ;
   
	@Override
	public String toString() {
		return "Action Task ";
	}
   
   
}