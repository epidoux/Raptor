package com.raptor.services.orm;


/**
 * Abstract class used to store datas to a database
 * @author erik
 * @version 1.0
 */
public abstract class ConnectionDB extends Connection {

	
	/**
	 * Present the Connection module
	 * @return the presentation string 
	 */
	public String toString(){
		return super.toString()+" through a database";
	}

}
