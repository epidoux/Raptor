package com.raptor.entities.condition;

import java.util.Calendar;

import com.raptor.entities.core.Article;



/**
 * define a date condition
 * @author erik
 *
 */
public class ConditionDate extends Condition<Calendar> {

	/**
	 * Constructor
	 */
	public ConditionDate(){
	
	}

	@Override
	public Boolean isExclude(Article article, Condition<?> condition) {
		// TODO Auto-generated method stub
		return null;
	}
		

}
