package com.raptor.entities.condition;

import com.raptor.entities.core.Article;

/**
 * Define an Integer condition
 * @author erik
 *
 */
public class ConditionInteger extends Condition<Integer> {

	
	/**
	 * Constructor
	 */
	public ConditionInteger(){
		
	}	
	
	@Override
	public Boolean isExclude(Article article, Condition<?> condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
