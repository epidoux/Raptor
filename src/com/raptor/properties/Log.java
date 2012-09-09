package com.raptor.properties;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Singleton logger 
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
public class Log {

	/**
	 * Instance of Log 
	 */
	private static Log INSTANCE;
	
	private static Logger logger = Logger.getLogger(Log.class.getName());
	
	
	/**
	 * private constructor
	 */
	private Log(){

	    BasicConfigurator.configure();
	}
	
	/**
	 * Return the Log instance
	 * @return instance the Log instance
	 */
	public static Log getInstance(){
		if(INSTANCE==null){
			INSTANCE = new Log();
		}
		return INSTANCE;
	}
	
	/**
	 * Log an info
	 * @param msg the message to log
	 */
	public void info(String msg){
		 logger.info(msg);
	}
	
	/**
	 * Log a debug
	 * @param msg the message to log
	 * @param e the exception
	 */
	public void debug(String msg, Throwable e){
		logger.debug(msg,e);
	}
	
	/**
	 * Log a warn
	 * @param msg the message to log
	 * @param e the exception
	 */
	public void warn(String msg, Throwable e){
		logger.warn(msg, e);
	}
	
	/**
	 * Log an error
	 * @param msg the message to log
	 * @param e the exception
	 */
	public void error(String msg, Throwable e){
		logger.error(msg,e);
	}

	
}
