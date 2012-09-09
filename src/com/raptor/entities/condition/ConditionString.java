package com.raptor.entities.condition;

import com.raptor.entities.core.Article;


/**
 * Define a string condition
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
