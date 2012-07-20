package com.raptor.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Singleton using to stored properties and all kind of resources for local user language in app.properties
 * @author erik
 * @version 1.0
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
