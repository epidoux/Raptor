package com.raptor.entities.condition;

import com.raptor.entities.core.Article;
import com.raptor.factories.ConditionFactory;

/**
 * Define an HTML condition needed for html crawl task
 * @author erik
 *
 */
public class ConditionHtml extends Condition<String> {

	/**
	 * Constructeur
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
					str = condition.getPositionRegex().replace(":pos:", "(.?)*");
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
