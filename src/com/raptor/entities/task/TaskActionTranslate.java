package com.raptor.entities.task;

import java.io.Serializable;

import com.raptor.properties.Constants;
import com.raptor.services.core.TranslatorService;

/**
 * This class represents a task which is used to translate a string from a language to another
 * @author erik
 * @version 1.0
 * type = translate
 * input = String
 * output = String
 */
public class TaskActionTranslate extends TaskAction implements Serializable {
   
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;
   
   /**
    * Original language 
    */
   protected String original_language;
   
   /**
    * Needed language
    */
   protected String needed_language;
   
   
   /**
    * Needed content
    * @var object (default string)
    */
   protected Object needed_content;

   /**
    * Translate a string from a language to another
    * @param filled the string
    * @return the string translate
    */
	@Override
	public Object execute(Object filled) throws Exception {
		//detect language here if needed... for further version
		this.original_content=filled;
		this.needed_content = TranslatorService.getInstance().translate(""+filled, this.original_language, this.needed_language);
		return this.needed_content;
	}
	
	
	
   public String getOriginal_language() {
	return original_language;
}



public void setOriginal_language(String original_language) {
	this.original_language = original_language;
}



public String getNeeded_language() {
	return needed_language;
}



public void setNeeded_language(String needed_language) {
	this.needed_language = needed_language;
}



public Object getNeeded_content() {
	return needed_content;
}



public void setNeeded_content(Object needed_content) {
	this.needed_content = needed_content;
}



@Override
   public String toString() {
   	return "Translate "+super.toString();
   }



@Override
public String getOriginalContentType() {
	return Constants.INTERNAL_CODE_TYPE_STRING;
}

}