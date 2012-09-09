package com.raptor.factories;

import java.io.IOException;
import com.raptor.properties.RobotProperties;
import com.raptor.services.orm.Connection;
import com.raptor.services.orm.ConnectionDBMysql;

/**
 * Factory Connection used to get the Connection module where are stored datas
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
