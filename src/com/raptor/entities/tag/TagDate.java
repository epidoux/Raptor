package com.raptor.entities.tag;

import com.raptor.factories.TagFactory;


/**
 * @author erik
 *
 */
public class TagDate extends Tag{
	
	/**
	 * Constructeur
	 */
	public TagDate(){
		super();
		this.setType(TagFactory.TYPE_DATE);
	}
	
}
