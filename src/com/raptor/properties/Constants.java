package com.raptor.properties;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Class containing all Constants for the APPLICATION
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
public class Constants {

	/**
	 * Name of the application
	 */
	public static final String APP_NAME="Raptor Task Manager";
	
	/**
	 * Version of the application
	 */
	public static final String APP_VERSION="1.0";
	
	/**
	 * Fichier texte de proprietes du robot
	 */
	public static String PROPERTY_ROBOT_FILE="META-INF/robot.properties";
		
	/**
	 * Key in properties file which give the smtp use by raptor to send mail
	 */
	public static final String EMAIL_SMTP = "robot.email.smtp";
	
	/**
	 * Key in properties file which give the email use by raptor to send email
	 */
	public static final String EMAIL = "robot.email";
	
	/**
	 * Key in properties file which precise if datas are stored in DB
	 */
	public static final String STORAGE_DB_ACTIVE="robot.storage.DB.active";
	
	/**
	 * Key in properties: Host db
	 */
	public static final String STORAGE_DB_HOST="robot.storage.DB.host";

	/**
	 * Key in properties : Database name
	 */
	public static final String STORAGE_DB_DBNAME="robot.storage.DB.dbname";

	/**
	 * Key in properties : Database login
	 */
	public static final String STORAGE_DB_LOGIN="robot.storage.DB.login";
	
	/**
	 * Key in properties : Database pass
	 */
	public static final String STORAGE_DB_PASS="robot.storage.DB.pass";
	
	/**
	 * key in properties : Service translator
	 */
	public static final String SERVICE_TRANSLATOR = "robot.service.translator";
	
	/**
	 * Code du login pour l'accès au mail du blog
	 */
	public static final String BLOG_LOGIN="robot.blog.login";
	
	/**
	 * Internal regex needed to replace content
	 */
	public static final String INTERNAL_REGEX_CONTENT = "[content]";
	
	/**
	 * Internal regex needed to replace source
	 */
	public static final String INTERNAL_REGEX_SOURCE = "[source]";
	

	/**
	 * Internal regex needed to replace original content (not modified or translated)
	 */
	public static final String INTERNAL_REGEX_ORIGINAL_CONTENT = "[original_content]";
	
	
	/**
	 * US date format
	 */
	public static DateFormat DF_US = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * FR date format
	 */
	public static DateFormat DF_FR = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	/**
	 * Code for a string
	 */
	public static final String INTERNAL_CODE_TYPE_STRING = "string";
	
	/**
	 * Code for an article
	 */
	public static final String INTERNAL_CODE_TYPE_ARTICLE = "article";
	
	/**
	 * Code for a list of article
	 */
	public static final String INTERNAL_CODE_TYPE_LIST_ARTICLE = "list<article>";
	
	
	
	
	/**
	 * Constructor
	 */
	private Constants(){
		
	}
	
}
