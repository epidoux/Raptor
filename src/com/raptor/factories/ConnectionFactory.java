package com.raptor.factories;

import java.io.IOException;
import com.raptor.properties.RobotProperties;
import com.raptor.services.orm.Connection;
import com.raptor.services.orm.ConnectionDBMysql;

/**
 * Factory Connection used to get the Connection module where are stored datas
 * @author erik
 * @version 1.0
 */
public class ConnectionFactory{

	/**
	 * Instance of the factory
	 */
	private static ConnectionFactory INSTANCE;
	
	/**
	 * Instance of Connection
	 */
	private Connection connexion;
	
	
	
	/**
	 * Constructor
	 * @throws IOException 
	 */
	private ConnectionFactory() throws IOException{
		if(RobotProperties.getInstance().isStorageDBActive()){
			this.connexion=new ConnectionDBMysql();
		}
		// To complete with other kind of storage
	}
	
	/**
	 * Get the instance of the factory
	 * @return the instance of connexion
	 * @throws IOException 
	 */
	public static ConnectionFactory getInstance() throws IOException{
		if(INSTANCE==null){
			INSTANCE=new ConnectionFactory();
		}
		return INSTANCE;
	}
	
	public Boolean close() {
		return this.connexion.close();
	}
	
	/**
	 * Return the connection to get datas
	 * @return the connection instance
	 */
	public Connection getConnection(){
		return this.connexion;
	}

	
}
