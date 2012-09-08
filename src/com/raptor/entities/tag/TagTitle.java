package com.raptor.entities.tag;

import com.raptor.factories.TagFactory;

/**
 * @author erik
 *
 */
public class TagTitle extends Tag{
	
	/**
	 * Constructeur
	 */
	public TagTitle(){
		super();
		this.setType(TagFactory.TYPE_TITLE);
	}
	
}
