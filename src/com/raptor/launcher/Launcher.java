package com.raptor.launcher;

import java.util.List;
import com.raptor.entities.core.Scenario;
import com.raptor.entities.core.User;
import com.raptor.factories.ConnectionFactory;
import com.raptor.properties.Log;
import com.raptor.services.orm.Connection;

/**
 * Launcher for the app
 * @author erik
 * @version 1.0
 *
 */
public class Launcher {

	/**
	 * @param args 0: login (optional)
	 */
	public static void main(String[] args) {
		
		//Let's start Raptor!!!
		Log.getInstance().info("Starting Raptor...");
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
