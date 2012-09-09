package com.raptor.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Singleton using to stored properties and all kind of resources for local user language in app.properties
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
public class Resources{
    
	/**
	 * Instance of LangProperties
	 */
	private static Resources INSTANCE;
		
	/**
	 * Instance of ResourceBundle
	 */
	private ResourceBundle bundle;
	
	/**
	 * Constructeur privé
	 */
	private Resources(){
		this.initialize(Locale.ENGLISH); 	
	}

	/**
	 * Return instance of LangProperties
	 * @return the instance
	 */
	public static Resources getInstance(){
		if(INSTANCE==null){
			INSTANCE = new Resources();
		}
		return INSTANCE;
	}

	/**
	 * Return the value in properties file with the key given
	 * @param key the key of the property
	 * @return the value which correspond to the key 
	 */
	public String getValue(String key){
		return this.bundle.getString(key);
	}
	
	/**
	 * Re-instanciate a new bundle with a new locale
	 * @param locale the new locale
	 */
	public void initialize(Locale locale){
		this.bundle= ResourceBundle.getBundle("locales", locale);
		
	}

}
