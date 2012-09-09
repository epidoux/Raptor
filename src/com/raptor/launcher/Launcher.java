package com.raptor.launcher;

import java.util.List;
import com.raptor.entities.core.Scenario;
import com.raptor.entities.core.User;
import com.raptor.factories.ConnectionFactory;
import com.raptor.properties.Constants;
import com.raptor.properties.Log;
import com.raptor.services.orm.Connection;

/**
 * Launcher for the app
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
public class Launcher {

	/**
	 * @param args 0: login (optional)
	 */
	public static void main(String[] args) {
		
		//Let's start Raptor!!!
		Log.getInstance().info("Starting "+Constants.APP_NAME+" on version "+Constants.APP_VERSION+" developped by Eric PIDOUX");
		try{
			Connection con = ConnectionFactory.getInstance().getConnection();
			//Retrieve all active users
			List<User> users = con.getActiveUsers();
			
			Log.getInstance().info("Retrieving "+users.size()+" user(s)!");
			for(User user : users){
				//Retrieve all scenario
				List<Scenario> scenarios = user.getScenarios();
				Log.getInstance().info(user+" has "+scenarios.size()+" active scenarios");
				for(Scenario scenario: scenarios){
					Log.getInstance().debug("Executing "+scenario+"...",null);
					scenario.execute();
					
					//Save the scenario
					if(con.saveScenario(scenario))Log.getInstance().info("Scenario saved");
				}
			}
			Log.getInstance().info("Raptor has done its job, shutting down.");
		
		}
		catch(Exception e){
			Log.getInstance().error("An error occured while executing Raptor", e);
		}

	}

}
