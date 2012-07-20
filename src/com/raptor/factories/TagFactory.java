package com.raptor.factories;

import java.io.IOException;
import java.util.List;

import com.raptor.entities.tag.Tag;
import com.raptor.entities.tag.TagContent;
import com.raptor.entities.tag.TagDate;
import com.raptor.entities.tag.TagTitle;

/**
 * Factory of Balise instance
 * @author erik
 *
 */
public class TagFactory {

	/**
	 * Instance of balise factory
	 */
	private static TagFactory INSTANCE;
	
	/**
	 * Key of the balise type content
	 */
	public static final String TYPE_CONTENT = "CONTENT";
	
	/**
	 * Key of the balise type Date
	 */
	public static final String TYPE_DATE = "DATE";
	
	/**
	 * Key of the balise type Title
	 */
	public static final String TYPE_TITLE = "TITLE";
	
	/**
	 * Key of the balise type MOTHER
	 */
	public static final String TYPE_MOTHER="MOTHER";
		
	/**
	 * private constructor
	 * @throws IOException 
	 */
	private TagFactory() throws IOException{
		
	}
	
	/**
	 * Get instance of the factory
	 * @return instance
	 * @throws IOException 
	 */
	public static TagFactory getInstance() throws IOException{
		if(INSTANCE==null){
			INSTANCE=new TagFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * Find the balise which the related key is given
	 * @param key the key of the balise
	 * @return the balise
	 */
	public Tag findBalise(String key){
		Tag b =null;
		if(TYPE_DATE.equals(key)){
			b= new TagDate();
		}
		else if(TYPE_TITLE.equals(key)){
			b= new TagTitle();
		}
		else if(TYPE_CONTENT.equals(key)){
			b= new TagContent();
		}
		else b = new Tag();
		return b;
	}
	
	/**
	 * Find the balise in a list with its type match with the code given
	 * @param list the list of balises
	 * @param key the key given
	 * @return balise 
	 */
	public Tag findBaliseInList(List<Tag> list, String key){
		Tag resu = null;
		for(Tag b : list){
			if(TYPE_DATE.equals(key)){
				if(b instanceof TagDate){
					resu=b;
					break;
				}
			}
			else if(TYPE_TITLE.equals(key)){
				if(b instanceof TagTitle){
					resu=b;
					break;
				}
			}
			else if(TYPE_CONTENT.equals(key)){
				if(b instanceof TagContent){
					resu=b;
					break;
				}
			}
			else if(TYPE_MOTHER.equals(key)){
				if(b instanceof Tag){
					resu=b;
					break;
				}
			}
		}
		return resu;
		
	}
	
	/**
	 * Retrieve the key of the balise
	 * @param the balise
	 * @return the type of the balise
	 */
	public String findType(Tag b){
		String resu=null;
		if(b instanceof TagDate){
			resu=TagFactory.TYPE_DATE;
		}
		else if(b instanceof TagTitle){
			resu=TagFactory.TYPE_TITLE;
			
		}
		else if(b instanceof TagContent){
			resu=TagFactory.TYPE_CONTENT;
			
		}
		else if(b instanceof Tag){
			resu=TagFactory.TYPE_MOTHER;
			
		}
		return resu;
	}
	
}
