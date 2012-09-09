package com.raptor.factories;

import java.util.HashMap;
import java.util.Map;

import com.raptor.entities.condition.Condition;
import com.raptor.entities.condition.ConditionDate;
import com.raptor.entities.condition.ConditionHtml;
import com.raptor.entities.condition.ConditionInteger;
import com.raptor.entities.condition.ConditionString;

/**
 * Factory of condition instance
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
public class ConditionFactory {

	/**
	 * Instance of Condition instance
	 */
	private static ConditionFactory INSTANCE;
	

	/**
	 * key of Conditions type string
	 */
	public static final String TYPE_STRING="STRING";
	
	/**
	 * key of  Conditions type html
	 */
	public static final String TYPE_HTML="HTML";
	
	/**
	 * key of  Conditions type date
	 */
	public static final String TYPE_DATE="DATE";

	/**
	 * key of Conditions type integer
	 */
	public static final String TYPE_INTEGER="INTEGER";
	
	
	public static final String EGAL = "=";
	
	public static final String DIFFERENT="!=";
	
	public static final String SUPERIEUR=">";
	
	public static final String INFERIEUR="<";
	
	public static final String SUPERIEUR_EGAL=">=";
	
	public static final String INFERIEUR_EGAL="<=";
	
	public static final String POSITION_BODY = "body";
	
	public static final String POSITION_URL = "url";
	
	public static final String POSITION_TITRE = "title";
	
	public static final String SIGNE_REGEX = ":pos:";
	
	/**
	 * Map of signes
	 */
	protected Map<String,String> mapSigne;
	/**
	 * private constructor
	 */
	private ConditionFactory() {
		
	}
	
	/**
	 * Retrieve the instance 
	 * @return the instance
	 */
	public static ConditionFactory getInstance() {
		if(INSTANCE==null){
			INSTANCE=new ConditionFactory();
		}
		return INSTANCE;
	}
	
	/**
	 * Return the map of signes 
	 * @return the map
	 */
	public Map<String,String> getMapSignes(){
		if(this.mapSigne==null){
			this.mapSigne= new HashMap<String,String>();
			this.mapSigne.put("DIFFERENT", DIFFERENT);
			this.mapSigne.put("EGAL", EGAL);
			this.mapSigne.put("INFERIEUR", INFERIEUR);
			this.mapSigne.put("SUPERIEUR", SUPERIEUR);
			this.mapSigne.put("INFERIEUR_EGAL", INFERIEUR_EGAL);
			this.mapSigne.put("SUPERIEUR_EGAL", SUPERIEUR_EGAL);
		}
		return this.mapSigne;
	}
	
	/**
	 * Define is the sign is DIFFERENT
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneDifferent(String signe){
		return this.findSigne(signe).equals(DIFFERENT);
	}
	
	/**
	 * Define is the sign is EGAL
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneEgal(String signe){
		return this.findSigne(signe).equals(EGAL);
	}
	
	/**
	 * Define is the sign is INFERIEUR
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneInferieur(String signe){
		return this.findSigne(signe).equals(INFERIEUR);
	}
	
	/**
	 * Define is the sign is INFERIEUR EGAL
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneInferieurEgal( String signe){
		return this.findSigne(signe).equals(INFERIEUR_EGAL);
	}
	
	/**
	 * Define is the sign is SUPERIEUR
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneSuperieur(String signe){
		return this.findSigne(signe).equals(SUPERIEUR);
	}
	
	/**
	 * Define is the sign is SUPERIEUR EGAL
	 * @param sign the sign to find
	 * @return true if it is or false
	 */
	public Boolean isSigneSuperieurEgal(String signe){
		return this.findSigne(signe).equals(SUPERIEUR_EGAL);
	}
	/**
	 * Find the sign 
	 * if it didn't find it, return EGAL 
	 * @param signe the sign
	 * @return the sign needed
	 */
	public String findSigne(String signe){
		String resu=EGAL;
		if(this.getMapSignes().containsValue(signe)){
			
			for(String val :this.getMapSignes().values()){
				if(val.equals(signe)){
					resu=signe;
				}
			}
		}
		return resu;
	}
	
	/**
	 * Return the condition related to the key
	 * @param key the key of the condition
	 * @return the condition in relation
	 */
	public Condition<?> create(String code){
		Condition<?> b =null;
		if(TYPE_STRING.equals(code)){
			b= new ConditionString();
			b.setType(TYPE_STRING);
		}
		else if(TYPE_INTEGER.equals(code)){
			b= new ConditionInteger();
			b.setType(TYPE_INTEGER);
		}
		else if(TYPE_DATE.equals(code)){
			b= new ConditionDate();
			b.setType(TYPE_DATE);
		}
		else if(TYPE_HTML.equals(code)){
			b= new ConditionHtml();
			b.setType(TYPE_HTML);
		}
		return b;
	}
	
}
