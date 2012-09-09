package com.raptor.entities.condition;

import com.raptor.entities.core.Article;
import com.raptor.factories.ConditionFactory;

/**
 * Define an HTML condition needed for html crawl task
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
public class ConditionHtml extends Condition<String> {

	/**
	 * Constructorr
	 */
	public ConditionHtml(){
	}

	@Override
	public Boolean isExclude(Article article,Condition<?> condition) {
		String str="";
		Boolean resultat = false;
		if(condition.getValue()!=null){
			if(condition.getPositionType().equals(ConditionFactory.POSITION_BODY)){
				//in the body (content of the page)
				if(condition.getPositionRegex().contains(":pos:")){
					//replace :pos: by the value
					str =condition.getPositionRegex().replace(ConditionFactory.SIGNE_REGEX, ""+condition.getValue());
					
					str = condition.getPositionRegex().replace(":pos:", "(.?)*");
					//extract the :pos: in the body if exists
					resultat = this.evaluate(str, (String) condition.value, article.getContent(),condition);
					
				}
				else{
					//the condition didn't content :pos:
					//get the value with the regex

					resultat = this.evaluate(condition.positionRegex,(String) condition.value, article.getContent(),condition);
				}				
			}
			else if(condition.getPositionType().equals(ConditionFactory.POSITION_URL)){
				//url;
				if(condition.getPositionRegex().contains(":pos:")){
					str = condition.getPositionRegex().replace(":pos:", "(.*)");
					//extract :pos: in the url

					resultat = this.evaluate(str, (String)condition.value, article.getLink(),condition);
				}
				else{
					//no :pos:

					resultat = this.evaluate(condition.getPositionRegex(),(String) condition.value, article.getLink(),condition);
				}
			}
			else if(condition.getPositionType().equals(ConditionFactory.POSITION_TITRE)){
				//title
				String titre = article.getTitle();
				if(condition.getPositionRegex().contains(":pos:")){
					str = condition.getPositionRegex().replace(":pos:", "(.?)*");
					//extract :pos: in url

					resultat = this.evaluate(str, (String)condition.value, titre,condition);
				}
				else{
					//no :pos:

					resultat = this.evaluate(condition.getPositionRegex(), (String)condition.value, titre,condition);
				}
			}
		}
	 
		return resultat;
	}
}
