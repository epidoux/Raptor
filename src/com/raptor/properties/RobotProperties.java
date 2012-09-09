package com.raptor.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Singleton managing the properties stored in the file conf.properties
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
public class RobotProperties {
	/**
	 * Instance of Properties 
	 */
	private static RobotProperties INSTANCE;
	
	/**
	 * properties file
	 */
	private java.util.Properties propRobot;
		
	/**
	 * Constructor
	 */
	private RobotProperties(){}

	/**
	 * Return the instance of proprietes file
	 * @return prop instance 
	 * @throws IOException 
	 */
	public static RobotProperties getInstance() throws IOException{
		if(INSTANCE==null){
			INSTANCE = new RobotProperties();
			INSTANCE.propRobot=new java.util.Properties();
			try {
				File f=new File(Constants.PROPERTY_ROBOT_FILE);
				FileInputStream in2 = new FileInputStream(f);
				INSTANCE.propRobot.load(in2);
				
			} catch (IOException e) {
				Log.getInstance().error("Impossible to load the properties file ", e);
				throw e;
			}
		}
		return INSTANCE;
	}
	
	/**
	 * Return the email smtp
	 * @return the smtp
	 */
	public String getEmailSMTP() {
		return INSTANCE.propRobot.getProperty(Constants.EMAIL_SMTP);
	}
	
	/**
	 * Return the email used by raptor to send emails
	 * @return the email
	 */
	public String getEmail() {
		return INSTANCE.propRobot.getProperty(Constants.EMAIL);
	}
	
	/**
	 * Return true if the datas are stored in a database
	 * @return true or false
	 */
	public Boolean isStorageDBActive() {
		return "true".equals(INSTANCE.propRobot.getProperty(Constants.STORAGE_DB_ACTIVE))?true:false;
	}
		
	/**
	 * Return the host name of the database
	 * @return the host name
	 */
	public String getStorageDBHost() {
		return INSTANCE.propRobot.getProperty(Constants.STORAGE_DB_HOST);
	}

	/**
	 * Return the database name
	 * @return the database name
	 */
	public String getStorageDBName() {
		return INSTANCE.propRobot.getProperty(Constants.STORAGE_DB_DBNAME);
	}
	
	/**
	 * Return the database login
	 * @return the database login
	 */
	public String getStorageDBLogin() {
		return INSTANCE.propRobot.getProperty(Constants.STORAGE_DB_LOGIN);
	}
	
	/**
	 * Return the database pass
	 * @return the database pass
	 */
	public String getStorageDBPass() {
		return INSTANCE.propRobot.getProperty(Constants.STORAGE_DB_PASS);
	}
	
	/**
	 * Return if the service translator is active
	 * @return true or false
	 */
	public Boolean isServiceTranslatorActive(){
		return "true".equals(INSTANCE.propRobot.getProperty(Constants.SERVICE_TRANSLATOR))?true:false;
	}
	
}
