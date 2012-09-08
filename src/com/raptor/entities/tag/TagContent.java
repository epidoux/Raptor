package com.raptor.entities.tag;

import com.raptor.factories.TagFactory;


/**
 * @author erik
 *
 */
public class TagContent extends Tag{
	
	
	/**
	 * Constructeur
	 */
	public TagContent(){
		super();
		this.setType(TagFactory.TYPE_CONTENT);
	}
	
	
}
