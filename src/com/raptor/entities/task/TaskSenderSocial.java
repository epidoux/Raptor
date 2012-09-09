package com.raptor.entities.task;

import java.io.Serializable;

/**
 * This class represents a task having something to send/publish on a social network in a scenario
 * type=sender_social
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