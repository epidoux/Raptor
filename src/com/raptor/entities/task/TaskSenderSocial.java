package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents a task having something to send/publish on a social network in a scenario
 * @author erik
 * @version 1.0
 * type=sender_social
 */
public class TaskSenderSocial extends TaskSender implements Serializable {
   /** Default value included to remove warning. Remove or modify at will. **/
   private static final long serialVersionUID = 1L;

@Override
public Object execute(Object filled) throws Exception {
	// TODO Auto-generated method stub
	return null;
}

   

@Override
public String toString() {
	return "Social "+super.toString();
}
}