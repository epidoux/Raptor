package com.raptor.entities.condition;

import com.raptor.entities.core.Article;


/**
 * Define a string condition
 * @author erik
 *
 */
public class ConditionString extends Condition<String> {

	/**
	 * Constructeur
	 */
	public ConditionString(){
	}

	@Override
	public Boolean isExclude(Article article, Condition<?> condition) {
		// TODO Auto-generated method stub
		return null;
	}
}
