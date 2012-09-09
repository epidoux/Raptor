package com.raptor.factories;

import java.io.IOException;
import java.util.List;

import com.raptor.entities.tag.Tag;
import com.raptor.entities.tag.TagContent;
import com.raptor.entities.tag.TagDate;
import com.raptor.entities.tag.TagTitle;

/**
 * Factory of Tag instance
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
 *
 */
public class TagFactory {

	/**
	 * Instance of tag factory
	 */
	private static TagFactory INSTANCE;
	
	/**
	 * Key of the tag type content
	 * Included or not in a MOTHER tag 
	 * Correspond to the content of the article
	 */
	public static final String TYPE_CONTENT = "CONTENT";
	
	/**
	 * Key of the tag type Date
	 * Included or not in a MOTHER tag 
	 * Correspond to the date of the article
	 */
	public static final String TYPE_DATE = "DATE";
	
	/**
	 * Key of the tag type Title
	 * Included or not in a MOTHER tag 
	 * Correspond to the title of the article
	 */
	public static final String TYPE_TITLE = "TITLE";
	
	/**
	 * Key of the tag type MOTHER
	 * Create an article for the content
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
	 * Find the tag which the related key is given
	 * @param key the key of the tag
	 * @return the tag
	 */
	public Tag findTag(String key){
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
	 * Find the tag in a list with its type match with the code given
	 * @param list the list of tags
	 * @param key the key given
	 * @return tag 
	 */
	public Tag findTagInList(List<Tag> list, String key){
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
	 * Retrieve the key of the tag
	 * @param the tag
	 * @return the type of the tag
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
